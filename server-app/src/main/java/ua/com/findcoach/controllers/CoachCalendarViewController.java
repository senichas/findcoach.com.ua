package ua.com.findcoach.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CoachCalendarViewController {
    @RequestMapping(method = RequestMethod.GET, value = "/coach/{coachUserName}/calendar.html")
    public ModelAndView viewPublicCalendarPage() {

        return new ModelAndView("publicCalendar");
    }
}
