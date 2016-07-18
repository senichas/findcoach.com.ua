package ua.com.findcoach.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.com.findcoach.api.CalendarResponse;
import ua.com.findcoach.api.deserializers.DateDeserializer;
import ua.com.findcoach.domain.Coach;
import ua.com.findcoach.services.CoachService;
import ua.com.findcoach.services.EventService;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/coach/{coachAlias}/calendar")
public class CoachCalendarController {

    @Autowired
    private CoachService coachService;

    @Autowired
    private EventService eventService;


    @RequestMapping(method = RequestMethod.GET, value = "/dashboard.html")
    public ModelAndView coachCalendarPage() {
        Map<String, Object> paramerters = new HashMap<>();
        paramerters.put("coachAlias", coachService.retrieveCurrentCoach().getAlias());

        return new ModelAndView("padawan-management/coachCalendarPage", paramerters);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/events")
    public
    @ResponseBody
    CalendarResponse fetchEventsForCoach(@PathVariable("coachAlias") String coachAlias,
                                         @RequestParam("startDate")
                                         @DateTimeFormat(pattern = DateDeserializer.DATE_PATTERN) LocalDate startDate,
                                         @RequestParam("endDate")
                                         @DateTimeFormat(pattern = DateDeserializer.DATE_PATTERN) LocalDate endDate) {
        Coach currentCoach = coachService.retrieveCurrentCoach();

        CalendarResponse response = new CalendarResponse();
        return response;
    }
}
