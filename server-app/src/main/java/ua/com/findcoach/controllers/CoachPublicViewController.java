package ua.com.findcoach.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.com.findcoach.api.CalendarEvent;
import ua.com.findcoach.api.CalendarResponse;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/coach/{coachUserName}")
public class CoachPublicViewController {
    // TODO - replace with Enum
    private static final String METHOD_LIST = "list";

    @RequestMapping(method = RequestMethod.GET, value = "/calendar.html")
    public ModelAndView viewPublicCalendarPage(@PathVariable("coachUserName") String coachUserName) {
        Map<String, Object> parameters = new HashMap<>();


        parameters.put("coachFirstName", "Винс");
        parameters.put("coachLastName", "Ломбарди");
        return new ModelAndView("coachPublicCalendar", parameters);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/calendar")
    public
    @ResponseBody
    CalendarResponse fetchEventsForCoach(@PathVariable("coachUserName") String coachUserName, @RequestParam("method") String method) {
        CalendarResponse response = new CalendarResponse();
        if (METHOD_LIST.equals(method)) {
            response.setStart(LocalDateTime.of(2015, 10, 19, 0, 0, 0));
            response.setEnd(LocalDateTime.of(2015, 10, 25, 23, 59, 59));
            response.setIssort(Boolean.TRUE);

            List<CalendarEvent> events = new ArrayList<>();

            CalendarEvent event1 = new CalendarEvent();
            event1.setId(1);
            event1.setSubject("TRX Training");
            event1.setStartTime(LocalDateTime.of(2015, 10, 22, 7, 0));
            event1.setEndTime(LocalDateTime.of(2015, 10, 25, 10, 0));
            event1.setAllDayEvent(Boolean.TRUE);
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
