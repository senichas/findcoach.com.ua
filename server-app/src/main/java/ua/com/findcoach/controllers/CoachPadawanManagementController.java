package ua.com.findcoach.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.com.findcoach.api.AddPadawanBasicInfo;
import ua.com.findcoach.domain.Coach;
import ua.com.findcoach.domain.Measure;
import ua.com.findcoach.domain.Padawan;
import ua.com.findcoach.domain.Program;
import ua.com.findcoach.services.CoachService;
import ua.com.findcoach.services.PadawanService;
import ua.com.findcoach.services.ProgramService;

import java.math.BigDecimal;
import java.time.LocalDate;

@Controller
@RequestMapping("/coach/{userAlias}/padawan-management")
public class CoachPadawanManagementController {

    @Autowired
    private CoachService coachService;

    @Autowired
    private PadawanService padawanService;

    @Autowired
    private ProgramService programService;

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

}
