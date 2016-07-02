package ua.com.findcoach.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.com.findcoach.api.PadawanCreateDto;
import ua.com.findcoach.api.PadawanDto;
import ua.com.findcoach.api.RestResponse;
import ua.com.findcoach.domain.Coach;
import ua.com.findcoach.domain.Padawan;
import ua.com.findcoach.exception.ValidationException;
import ua.com.findcoach.services.CoachService;
import ua.com.findcoach.services.ConverterService;
import ua.com.findcoach.services.PadawanService;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

    @RequestMapping(method = RequestMethod.PUT, value = "/{coachAlias}/padawan")
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
}
