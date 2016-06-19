package ua.com.findcoach.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.com.findcoach.api.*;
import ua.com.findcoach.converters.CycleConverterService;
import ua.com.findcoach.converters.ProgramConverterService;
import ua.com.findcoach.domain.Coach;
import ua.com.findcoach.domain.Cycle;
import ua.com.findcoach.domain.Program;
import ua.com.findcoach.i18n.LocalizedMessageResolver;
import ua.com.findcoach.services.CoachService;
import ua.com.findcoach.services.CycleService;
import ua.com.findcoach.services.EventService;
import ua.com.findcoach.services.ProgramService;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/coach")
public class CoachProgramController {

    @Autowired
    private LocalizedMessageResolver messageResolver;

    @Autowired
    private CoachService coachService;

    @Autowired
    private ProgramService programService;

    @Autowired
    private CycleService cycleService;

    @Autowired
    private EventService eventService;

    @Autowired
    private CycleConverterService cycleConverterService;

    @Autowired
    private ProgramConverterService programConverterService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/{coachAlias}/padawans.html")
    public ModelAndView receiveCoachProgramPadawans(@PathVariable String coachAlias) {
        Map<String, Object> params = new HashMap<>();
        Coach currentCoach = coachService.retrieveCurrentCoach();
        params.put("coachAlias", currentCoach.getAlias());
        List<PadawanDto> padawans = new ArrayList<>();
        currentCoach
                .getProgramList()
                .stream()
                .collect(Collectors.groupingBy(p -> p.getPadawan()))
                .entrySet().stream()
                .forEach(entry ->
                {
                    PadawanDto padawanDto = new PadawanDto(
                            entry.getKey().getPadawanId(),
                            entry.getKey().getFirstName(),
                            entry.getKey().getLastName(),
                            entry.getKey().getEmail(),
                            entry.getKey().getGender(),
                            entry.getKey().getBirthday(),
                            entry.getKey().isActive());
                    entry.getValue().stream()
                            .forEach(program -> padawanDto.getPadawanProgramDTOList()
                                    .add(new ProgramDto(program.getName()
                                            , program.getGoal()
                                            , program.getProgramId()
                                            , program.getStartDate()
                                            , program.getEndDate())));
                    padawans.add(padawanDto);
                });


        params.put("padawansList", padawans);
        params.put("formatter", DateTimeFormatter.ofPattern("YYYY-MM-dd"));
        return new ModelAndView("padawan-management/padawans", params);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{coachAlias}/program/{programId}.html")
    public ModelAndView programDetailPage(@PathVariable String coachAlias, @PathVariable Integer programId) {
        Map<String, Object> parameters = new HashMap<>();

        Program program = programService.findProgramById(programId);

        parameters.put("programName", program.getName());
        parameters.put("programId", program.getProgramId());
        parameters.put("coachAlias", coachAlias);
        List<CycleDto> cycleDtos = cycleConverterService.convertCyclesListToDtos(program.getCycles());
        parameters.put("cycles", cycleDtos);
        parameters.put("formatter", DateTimeFormatter.ofPattern("YYYY-MM-dd"));
        parameters.put("timeFormatter", DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm"));
        return new ModelAndView("padawan-management/programDetails", parameters);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{coachAlias}/program/{programId}")
    @ResponseBody
    public ProgramDetailsDto programDetails(@PathVariable String coachAlias, @PathVariable Integer programId) {
        Program program = programService.findProgramById(programId);

        ProgramDetailsDto programDetailsDto = programConverterService.convertToDetailedDto(program);

        return programDetailsDto;

    }

    @RequestMapping(method = RequestMethod.GET, value = "/{coachAlias}/program/{programId}/cycle.html")
    public ModelAndView createProgramCyclePage(@PathVariable String coachAlias, @PathVariable Integer programId) {
        Map<String, Object> parameters = new HashMap<>();

        Program program = programService.findProgramById(programId);

        parameters.put("message", messageResolver.getMessage("titlepage.welcome.coach"));
        parameters.put("programName", program.getName());
        parameters.put("programId", program.getProgramId());

        Cycle cycle = new Cycle();
        cycle.setName("");
        cycle.setNotes("");
        cycle.setCycleId(-1);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 3);
        parameters.put("cycle", cycle);
        parameters.put("cycleAction", "Добавить");

        return new ModelAndView("padawan-management/programCycleDetails", parameters);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{coachAlias}/program/{programId}/cycle/{cycleId}.html")
    public ModelAndView updateProgramCyclePage(@PathVariable String coachAlias, @PathVariable Integer programId,
                                               @PathVariable Integer cycleId) {
        Map<String, Object> parameters = new HashMap<>();

        Program program = programService.findProgramById(programId);

        parameters.put("message", messageResolver.getMessage("titlepage.welcome.coach"));
        parameters.put("programName", program.getName());
        parameters.put("programId", program.getProgramId());
        parameters.put("cycle", cycleService.findCycleById(cycleId));
        parameters.put("cycleAction", "Изменить");

        return new ModelAndView("padawan-management/programCycleDetails", parameters);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{coachAlias}/program/{programId}/cycle")
    @ResponseBody
    public RestResponse saveCycle(@PathVariable String coachAlias, @PathVariable Integer programId,
                                  @RequestBody CycleDto cycleDto) {
        Cycle existingCycle = cycleService.findCycleById(cycleDto.getCycleId());

        Cycle cycleToSave = existingCycle == null ? new Cycle() : existingCycle;
        cycleToSave.setName(cycleDto.getName());
        cycleToSave.setNotes(cycleDto.getNotes());

        if (cycleToSave.getCycleId() != null) {
            cycleService.save(cycleToSave);
        } else {
            Program program = programService.findProgramById(programId);
            if (program.getCycles() == null) {
                program.setCycles(new ArrayList<>());
            }
            program.getCycles().add(cycleToSave);
            programService.saveProgram(program);
        }

        RestResponse response = new RestResponse();
        return response;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{coachAlias}/program/{programId}/cycle/{cycleId}/training")
    @ResponseBody
    public RestResponse saveNewTraining(@PathVariable String coachAlias, @PathVariable Integer programId, @PathVariable Integer cycleId) {

        Program program = programService.findProgramById(programId);


        RestResponse response = new RestResponse();
        return response;
    }
}
