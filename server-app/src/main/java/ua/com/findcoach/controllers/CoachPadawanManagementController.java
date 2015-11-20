package ua.com.findcoach.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.com.findcoach.services.CoachService;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/coach/{userAlias}/padawan-management")
public class CoachPadawanManagementController {

    @Autowired
    private CoachService coachService;

    @RequestMapping(value = "/add-padawan-step1.html", method = RequestMethod.GET)
    public ModelAndView addPadawanForm() {
        Map<String, Object> paramerters = new HashMap<>();
        paramerters.put("coachAlias", coachService.retrieveCurrentCoach().getAlias());

        return new ModelAndView("padawan-management/add-padawan-step1", paramerters);
    }
}
