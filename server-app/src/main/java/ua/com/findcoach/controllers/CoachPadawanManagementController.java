package ua.com.findcoach.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.com.findcoach.api.AddPadawanBasicInfo;
import ua.com.findcoach.api.EditPadawanInfoRequest;
import ua.com.findcoach.api.PadawanDto;
import ua.com.findcoach.domain.Coach;
import ua.com.findcoach.domain.Measure;
import ua.com.findcoach.domain.Padawan;
import ua.com.findcoach.domain.Program;
import ua.com.findcoach.services.CoachService;
import ua.com.findcoach.services.PadawanService;
import ua.com.findcoach.services.ProgramService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/coach/{userAlias}/padawan-management")
public class CoachPadawanManagementController {

    @Autowired
    private CoachService coachService;

    @Autowired
    private PadawanService padawanService;

    @Autowired
    private ProgramService programService;

    @RequestMapping(value = "/add-padawan.html", method = RequestMethod.GET)
    public ModelAndView addPadawanForm() {
        Map<String, Object> paramerters = new HashMap<>();
        paramerters.put("coachAlias", coachService.retrieveCurrentCoach().getAlias());

        return new ModelAndView("padawan-management/add-padawan", paramerters);
    }

    @RequestMapping(value = "/basic-info", method = RequestMethod.POST)
    @ResponseBody
    public String addPadawan(@RequestBody AddPadawanBasicInfo padawanBasicInfo) {
        Coach currentCoach = coachService.retrieveCurrentCoach();

        Padawan newPadawan = new Padawan();
        newPadawan.setCreatedBy(currentCoach);
        newPadawan.setEmail(padawanBasicInfo.getPadawanData().getEmail());
        String[] parsedName = padawanBasicInfo.getPadawanData().getName().split(" ");
        newPadawan.setFirstName(parsedName[0]);
        newPadawan.setLastName(parsedName[1]);
        newPadawan.setActive(Boolean.TRUE);
        newPadawan.setGender(padawanBasicInfo.getPadawanData().getGender());


        Padawan savedPadawan = padawanService.save(newPadawan);

        Measure firstMeasure = new Measure();
        firstMeasure.setMeasureDate(LocalDate.now());
        firstMeasure.setWeight(BigDecimal.valueOf(padawanBasicInfo.getPadawanMeasurement().getWeight()));
        firstMeasure.setHeight(padawanBasicInfo.getPadawanMeasurement().getHeight());
        firstMeasure.setFatPercentage(padawanBasicInfo.getPadawanMeasurement().getFatPercentage());
        firstMeasure.setPadawan(savedPadawan);


        savedPadawan = padawanService.addMeasureToPadawan(savedPadawan, firstMeasure);
        Program program = new Program();
        program.setCoach(currentCoach);
        program.setPadawan(savedPadawan);
        program.setStartDate(padawanBasicInfo.getPadawanProgram().getStartDate());
        program.setEndDate(padawanBasicInfo.getPadawanProgram().getEndDate());
        program.setGoal(padawanBasicInfo.getPadawanProgram().getGoal());
        program.setName(padawanBasicInfo.getPadawanProgram().getName());


        Program p1 = programService.saveProgram(program);

        return "";
    }

    @RequestMapping(value = "/{padawanId}/edit-padawan.html", method = RequestMethod.GET)
    public ModelAndView editPadawanPage(@PathVariable Integer padawanId) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("coachAlias", coachService.retrieveCurrentCoach().getAlias());
        Padawan padawan = padawanService.findById(padawanId);

        parameters.put("padawan", new PadawanDto(padawan.getPadawanId(),
                padawan.getFirstName(),
                padawan.getLastName(),
                padawan.getEmail(),
                padawan.getGender(),
                padawan.getBirthday(),
                padawan.isActive()));
        parameters.put("formatter", DateTimeFormatter.ofPattern("YYYY-MM-dd"));
        return new ModelAndView("padawan-management/edit-padawan", parameters);
    }

    @RequestMapping(value = "/{padawanId}/edit-padawan.html", method = RequestMethod.POST)
    @ResponseBody
    public String updatePadawan(@PathVariable Integer padawanId, @RequestBody EditPadawanInfoRequest editPadawanInfoRequest) {
        Padawan padawan = padawanService.findById(padawanId);

        padawan.setFirstName(editPadawanInfoRequest.getFirstName());
        padawan.setLastName(editPadawanInfoRequest.getLastName());
        padawan.setEmail(editPadawanInfoRequest.getEmail());
        padawan.setGender(editPadawanInfoRequest.getGender());
        padawan.setBirthday(editPadawanInfoRequest.getBirthday());
        padawan.setActive(editPadawanInfoRequest.isActive());
        Padawan savedPadawan = padawanService.saveAndFlush(padawan);
        return "";
    }

}
