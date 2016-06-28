package ua.com.findcoach.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.com.findcoach.api.*;
import ua.com.findcoach.converters.CycleConverterService;
import ua.com.findcoach.converters.ProgramConverterService;
import ua.com.findcoach.domain.Coach;
import ua.com.findcoach.domain.Cycle;
import ua.com.findcoach.domain.Event;
import ua.com.findcoach.domain.Program;
import ua.com.findcoach.exception.ValidationException;
import ua.com.findcoach.i18n.LocalizedMessageResolver;
import ua.com.findcoach.services.CoachService;
import ua.com.findcoach.services.CycleService;
import ua.com.findcoach.services.EventService;
import ua.com.findcoach.services.ProgramService;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @RequestMapping(method = RequestMethod.GET, value = "/{coachAlias}/program/{programId}/cycle/{cycleId}")
    @ResponseBody
    public SimpleCycleDto updateProgramCyclePage(@PathVariable String coachAlias, @PathVariable Integer programId,
                                                 @PathVariable Integer cycleId) {
        Cycle cycle = cycleService.findCycleById(cycleId);

        SimpleCycleDto simpleCycleDto = new SimpleCycleDto();
        simpleCycleDto.setName(cycle.getName());
        simpleCycleDto.setDescription(cycle.getNotes());

        return simpleCycleDto;
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
    public RestResponse saveNewTraining(@PathVariable String coachAlias, @PathVariable Integer programId, @PathVariable Integer cycleId,
                                        @RequestBody @Valid AddNewTrainingRequest addNewTrainingRequest, BindingResult validationResult) {

        RestResponse response = new RestResponse();

        Program program = programService.findProgramById(programId);

        // TODO - this solution make detailed validation
        if (validationResult.hasErrors()) {
            throw new ValidationException(validationResult.getAllErrors().get(0).getDefaultMessage());
        }

        Event event = eventService.composeNewEvent(addNewTrainingRequest.getTitle(), addNewTrainingRequest.getDescription(),
                addNewTrainingRequest.getRepeatOnDays(), addNewTrainingRequest.getRepeatTerm(), addNewTrainingRequest.getStartDate(),
                addNewTrainingRequest.getDuration());

        Cycle cycle = cycleService.findCycleById(cycleId);
        cycle.getEvents().add(event);

        cycleService.save(cycle);

        return response;
    }
}
