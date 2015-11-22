package ua.com.findcoach.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ua.com.findcoach.api.AddPadawanBasicInfo;
import ua.com.findcoach.domain.Coach;
import ua.com.findcoach.domain.Measure;
import ua.com.findcoach.domain.Padawan;
import ua.com.findcoach.services.CoachService;
import ua.com.findcoach.services.PadawanService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/coach/{userAlias}/padawan-management")
public class CoachPadawanManagementController {

    @Autowired
    private CoachService coachService;

    @Autowired
    private PadawanService padawanService;

    @RequestMapping(value = "/add-padawan.html", method = RequestMethod.GET)
    public ModelAndView addPadawanForm() {
        Map<String, Object> paramerters = new HashMap<>();
        paramerters.put("coachAlias", coachService.retrieveCurrentCoach().getAlias());

        return new ModelAndView("padawan-management/add-padawan", paramerters);
    }

    @RequestMapping(value = "/basic-info", method = RequestMethod.PUT)
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

        Measure firstMeasure = new Measure();
        firstMeasure.setMeasureDate(LocalDate.now());
        firstMeasure.setWeight(BigDecimal.valueOf(padawanBasicInfo.getPadawanMeasurement().getWeight()));
        firstMeasure.setHeight(padawanBasicInfo.getPadawanMeasurement().getHeight());
        firstMeasure.set

        Padawan savedPadawan = padawanService.save(newPadawan);

        return "";
    }


}
