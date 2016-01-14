package ua.com.findcoach.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.com.findcoach.api.CalendarEvent;
import ua.com.findcoach.api.CalendarResponse;
import ua.com.findcoach.domain.Coach;
import ua.com.findcoach.services.CoachService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/coach/{coachAlias}")
public class CoachCalendarController {
    // TODO - replace with Enum
    private static final String METHOD_LIST = "list";

    @Autowired
    private CoachService coachService;


    @RequestMapping(method = RequestMethod.GET, value = "/calendar.html")
    public ModelAndView coachCalendarPage() {
        Map<String, Object> paramerters = new HashMap<>();
        paramerters.put("coachAlias", coachService.retrieveCurrentCoach().getAlias());

        return new ModelAndView("padawan-management/coachCalendarPage", paramerters);
    }

    /**
     * TODO - refactor JS and code to use methods POST, GET, PUT, DELETE instead request parameter method
     */
    @RequestMapping(method = RequestMethod.POST, value = "/calendar")
    public
    @ResponseBody
    CalendarResponse fetchEventsForCoach(@PathVariable("coachAlias") String coachUserName, @RequestParam("method") String method) {
        Coach currentCoach = coachService.retrieveCurrentCoach();

        CalendarResponse response = new CalendarResponse();
        if (METHOD_LIST.equals(method)) {
            response.setStart(LocalDateTime.of(2016, 1, 10, 0, 0, 0));
            response.setEnd(LocalDateTime.of(2016, 1, 16, 23, 59, 59));
            response.setIssort(Boolean.TRUE);

            List<CalendarEvent> events = new ArrayList<>();

            CalendarEvent event1 = new CalendarEvent();
            event1.setId(1);
            event1.setSubject("TRX Training");
            event1.setStartTime(LocalDateTime.of(2016, 1, 14, 7, 0));
            event1.setEndTime(LocalDateTime.of(2016, 1, 14, 10, 0));
            event1.setAllDayEvent(Boolean.FALSE);
            event1.setCrossDay(0);
            event1.setRecurringEvent(0);
            event1.setColor(2);
            event1.setEditable(0);
            event1.setLocation("Start gym");
            event1.setAttends("");

            events.add(event1);

            response.setEvents(events);
        }
        return response;
    }
}
