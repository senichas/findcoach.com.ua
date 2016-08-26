package ua.com.findcoach.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.com.findcoach.api.*;
import ua.com.findcoach.converters.ConverterService;
import ua.com.findcoach.converters.ProgramConverterService;
import ua.com.findcoach.domain.Coach;
import ua.com.findcoach.domain.Padawan;
import ua.com.findcoach.domain.Program;
import ua.com.findcoach.exception.ValidationException;
import ua.com.findcoach.services.CoachService;
import ua.com.findcoach.services.PadawanService;
import ua.com.findcoach.services.ProgramService;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/coach")
public class CoachPadawanManagementController {

    @Autowired
    private CoachService coachService;

    @Autowired
    private PadawanService padawanService;

    @Autowired
    private ConverterService converterService;

    @Autowired
    private ProgramConverterService programConverterService;

    @Autowired
    private ProgramService programService;


    @RequestMapping(method = RequestMethod.GET, value = "/{coachAlias}/padawans.html")
    public ModelAndView receiveCoachProgramPadawans(@PathVariable String coachAlias) {
        Map<String, Object> params = new HashMap<>();
        Coach currentCoach = coachService.retrieveCurrentCoach();
        params.put("coachAlias", currentCoach.getAlias());

        params.put("formatter", DateTimeFormatter.ofPattern("YYYY-MM-dd"));
        return new ModelAndView("padawan-management/padawans", params);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/{coachAlias}/padawans")
    public List<PadawanDto> retrievePadawansAndProgramsList(@PathVariable String coachAlias) {
        List<Padawan> padawans = padawanService.retrievePadawansForCoach(coachAlias);
        return converterService.convertPadawansToDtos(padawans);

    }

    @RequestMapping(method = RequestMethod.POST, value = "/{coachAlias}/padawan")
    @ResponseBody
    public RestResponse createNewPadawan(@PathVariable String coachAlias, @Valid @RequestBody PadawanCreateDto padawanDto,
                                         BindingResult validationResult) {
        // TODO - this solution make detailed validation
        if (validationResult.hasErrors()) {
            throw new ValidationException(validationResult.getAllErrors().get(0).getDefaultMessage());
        }

        RestResponse response = new RestResponse();
        Coach coach = coachService.retrieveCurrentCoach();
        Padawan padawan = new Padawan();

        padawan.setFirstName(padawanDto.getFirstName());
        padawan.setLastName(padawanDto.getLastName());
        padawan.setCreatedBy(coach);
        padawan.setBirthday(padawanDto.getBirthday());
        padawan.setEmail(padawanDto.getEmail());
        padawan.setGender(padawanDto.getGender());
        padawan.setNotes(padawanDto.getNotes());
        padawan.setActive(Boolean.TRUE);

        padawanService.save(padawan);

        return response;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{coachAlias}/padawan")
    @ResponseBody
    public RestResponse updatePadawan(@PathVariable String coachAlias, @Valid @RequestBody PadawanCreateDto padawanDto,
                                      BindingResult validationResult) {
        // TODO - this solution make detailed validation
        if (validationResult.hasErrors()) {
            throw new ValidationException(validationResult.getAllErrors().get(0).getDefaultMessage());
        }

        Coach coach = coachService.retrieveCurrentCoach();
        Padawan padawan = padawanService.findByIdAndCoachAlias(padawanDto.getPadawanId(), coachAlias);

        padawan.setFirstName(padawanDto.getFirstName());
        padawan.setLastName(padawanDto.getLastName());
        padawan.setCreatedBy(coach);
        padawan.setBirthday(padawanDto.getBirthday());
        padawan.setEmail(padawanDto.getEmail());
        padawan.setGender(padawanDto.getGender());
        padawan.setNotes(padawanDto.getNotes());
        padawan.setActive(Boolean.TRUE);

        padawanService.save(padawan);

        RestResponse response = new RestResponse();
        return response;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/{coachAlias}/padawan/{padawanId}")
    public PadawanDto retrievePadawan(@PathVariable String coachAlias, @PathVariable Integer padawanId) {
        return converterService.convertPadawanToDto(padawanService.findByIdAndCoachAlias(padawanId, coachAlias));
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/{coachAlias}/padawan/{padawanId}/program/{programId}")
    public ProgramDetailsDto retrieveProgram(@PathVariable String coachAlias, @PathVariable Integer padawanId,
                                      @PathVariable Integer programId) {
        Program program = programService.findProgramByProgramIdAndPadawanIdAndProgramId(coachAlias, padawanId, programId);

        ProgramDetailsDto programDetailsDto = programConverterService.convertToDetailedDto(program);

        return programDetailsDto;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT, value = "/{coachAlias}/padawan/{padawanId}/program/{programId}")
    public ProgramDto updateProgram(@PathVariable String coachAlias, @PathVariable Integer padawanId,
                                    @PathVariable Integer programId, @RequestBody ProgramRequest programRequest) {
        Program program = programService.findProgramByProgramIdAndPadawanIdAndProgramId(coachAlias, padawanId, programId);
        program.setName(programRequest.getName());
        program.setGoal(programRequest.getGoal());
        program = programService.saveProgram(program);
        return programConverterService.convertProgramToDto(program);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/{coachAlias}/padawan/{padawanId}/program")
    public RestResponse createProgram(@PathVariable String coachAlias, @PathVariable Integer padawanId,
                                      @RequestBody ProgramRequest programRequest) {
        Padawan padawan = padawanService.findByIdAndCoachAlias(padawanId, coachAlias);
        Coach coach = coachService.retrieveCurrentCoach();
        Program program = new Program();
        program.setName(programRequest.getName());
        program.setGoal(programRequest.getGoal());
        program.setCoach(coach);
        program.setPadawan(padawan);
        programService.saveProgram(program);
        return new RestResponse();
    }


}
