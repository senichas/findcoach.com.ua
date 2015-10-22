package ua.com.findcoach.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/coach/{coachUserName}")
public class CoachPublicViewController {
    @RequestMapping(method = RequestMethod.GET, value = "/calendar.html")
    public ModelAndView viewPublicCalendarPage(@PathVariable("coachUserName") String coachUserName) {
        Map<String, Object> parameters = new HashMap<>();


        parameters.put("coachFirstName", "Винс");
        parameters.put("coachLastName", "Ломбарди");
        return new ModelAndView("coachPublicCalendar", parameters);
    }
}
