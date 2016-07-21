package ua.com.findcoach.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.com.findcoach.api.*;
import ua.com.findcoach.converters.CycleConverterService;
import ua.com.findcoach.converters.ProgramConverterService;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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


    @RequestMapping(method = RequestMethod.GET, value = "/{coachAlias}/padawan/{padawanId}/program/{programId}.html")
    public ModelAndView programDetailPage(@PathVariable String coachAlias, @PathVariable Integer padawanId,
                                          @PathVariable Integer programId) {
        Map<String, Object> parameters = new HashMap<>();

        Program program = programService.findProgramById(programId);

        parameters.put("programName", program.getName());
        parameters.put("programId", program.getProgramId());
        parameters.put("coachAlias", coachAlias);
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
    public SimpleCycleDto retrieveCycle(@PathVariable String coachAlias, @PathVariable Integer programId,
                                        @PathVariable Integer cycleId) {
        Cycle cycle = cycleService.findCycleById(cycleId);

        SimpleCycleDto simpleCycleDto = new SimpleCycleDto();
        simpleCycleDto.setName(cycle.getName());
        simpleCycleDto.setDescription(cycle.getNotes());

        return simpleCycleDto;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{coachAlias}/program/{programId}/cycle")
    @ResponseBody
    public RestResponse addCycleToProgram(@PathVariable String coachAlias, @PathVariable Integer programId,
                                          @RequestBody @Valid SimpleCycleDto cycleDto) {
        programService.addNewCycleToProgram(programId, cycleDto.getName(), cycleDto.getDescription());

        return new RestResponse();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{coachAlias}/program/{programId}/cycle/{cycleId}")
    @ResponseBody
    public RestResponse updateCycleInProgram(@PathVariable String coachAlias, @PathVariable Integer programId,
                                             @PathVariable Integer cycleId, @RequestBody @Valid SimpleCycleDto cycleDto) {
        cycleService.updateCycle(cycleId, cycleDto.getName(), cycleDto.getDescription());

        return new RestResponse();
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
