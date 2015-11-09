package ua.com.findcoach.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/coach/{userAlias}/padawan-management")
public class CoachPadawanManagementController {
    @RequestMapping(value = "/add-padawan.html", method = RequestMethod.GET)
    public ModelAndView addPadawanForm() {

        return new ModelAndView("padawan-management/add-padawan");
    }
}
