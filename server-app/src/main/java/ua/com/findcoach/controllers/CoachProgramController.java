package ua.com.findcoach.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.com.findcoach.api.CycleDto;
import ua.com.findcoach.api.PadawanDTO;
import ua.com.findcoach.api.RestResponse;
import ua.com.findcoach.api.TrainingDto;
import ua.com.findcoach.domain.Coach;
import ua.com.findcoach.domain.Cycle;
import ua.com.findcoach.domain.Program;
import ua.com.findcoach.services.CoachService;
import ua.com.findcoach.services.ProgramService;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@RequestMapping("/coach")
public class CoachProgramController {

    @Autowired
    private CoachService coachService;

    @Autowired
    private ProgramService programService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/{coachAlias}/padawans")
    public ModelAndView recieveCoachProgramPadawans(@PathVariable String coachAlias) {
        Map<String, Object> params = new HashMap<>();
        Coach currentCoach = coachService.retrieveCurrentCoach();
        params.put("coachAlias", currentCoach.getAlias());
        List<PadawanDTO> padawans = new ArrayList<>();
        currentCoach
                .getProgramList()
                .stream()
                .forEach(program -> padawans.add(new PadawanDTO(
                        program.getPadawan().getPadawanId()
                        , program.getPadawan().getFirstName()
                        , program.getPadawan().getLastName()
                        , program.getPadawan().getEmail()
                        , program.getPadawan().getGender()
                        , program.getProgramId()
                )));
        params.put("padawansList", padawans);
        return new ModelAndView("padawan-management/padawans", params);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{coachAlias}/program/{programId}.html")
    public ModelAndView programDetailPage(@PathVariable String coachAlias, @PathVariable Integer programId) {
        Map<String, Object> parameters = new HashMap<>();

        Program program = programService.findProgramById(programId);

        parameters.put("programName", program.getName());
        parameters.put("programId", program.getProgramId());
        parameters.put("coachAlias", coachAlias);
        parameters.put("cycles", program.getCycles());
        parameters.put("formatter", DateTimeFormatter.ofPattern("YYYY-MM-dd"));
        return new ModelAndView("padawan-management/programDetails", parameters);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{coachAlias}/program/{programId}/cycle.html")
    public ModelAndView programCyclePage(@PathVariable String coachAlias, @PathVariable Integer programId) {
        Map<String, Object> parameters = new HashMap<>();

        Program program = programService.findProgramById(programId);

        parameters.put("programName", program.getName());
        parameters.put("programId", program.getProgramId());


        return new ModelAndView("padawan-management/programCycleDetails", parameters);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{coachAlias}/program/{programId}/cycle")
    @ResponseBody
    public RestResponse saveCycle(@PathVariable String coachAlias, @PathVariable Integer programId, @RequestBody CycleDto cycleDto) {
        Program program = programService.findProgramById(programId);

        Cycle cycle = new Cycle();
        cycle.setName(cycleDto.getName());
        cycle.setNotes(cycleDto.getNotes());
        Instant startInstant = Instant.ofEpochMilli(cycleDto.getStartDate());
        LocalDateTime startDateTime = LocalDateTime.ofInstant(startInstant, TimeZone.getDefault().toZoneId());
        cycle.setStartDate(startDateTime.toLocalDate());

        Instant endInstant = Instant.ofEpochMilli(cycleDto.getEndDate());
        LocalDateTime endDateTime = LocalDateTime.ofInstant(endInstant, TimeZone.getDefault().toZoneId());
        cycle.setEndDate(endDateTime.toLocalDate());

        if (program.getCycles() == null) {
            program.setCycles(new ArrayList<>());

        }
        program.getCycles().add(cycle);
        programService.saveProgram(program);

        RestResponse response = new RestResponse();

        return response;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{coachAlias}/program/{programId}/cycle/{cycleId}/training.html")
    public ModelAndView addTrainingForm(@PathVariable String coachAlias, @PathVariable Integer programId, @PathVariable Integer cycleId) {
        Map<String, Object> parameters = new HashMap<>();

        Program program = programService.findProgramById(programId);

        parameters.put("coachAlias", coachAlias);
        parameters.put("programName", program.getName());
        parameters.put("programId", program.getProgramId());
        for (Cycle cycle : program.getCycles()) {
            if (cycle.getCycleId().compareTo(cycleId) == 0) {
                parameters.put("cycleId", cycle.getCycleId());
                parameters.put("cycleName", cycle.getName());
                break;
            }
        }

        return new ModelAndView("padawan-management/trainingDetails", parameters);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{coachAlias}/program/{programId}/cycle/{cycleId}/training")
    @ResponseBody
    public RestResponse saveNewTraining(@PathVariable String coachAlias, @PathVariable Integer programId, @PathVariable Integer cycleId, @RequestBody TrainingDto trainingDto) {

        Program program = programService.findProgramById(programId);

        RestResponse response = new RestResponse();
        return response;
    }
}
