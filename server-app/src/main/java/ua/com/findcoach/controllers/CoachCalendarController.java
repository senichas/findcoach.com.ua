package ua.com.findcoach.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.com.findcoach.api.CalendarEvent;
import ua.com.findcoach.api.CalendarResponse;
import ua.com.findcoach.api.deserializers.DateTimeDeserializer;
import ua.com.findcoach.converters.EventConverterService;
import ua.com.findcoach.domain.Event;
import ua.com.findcoach.services.CoachService;
import ua.com.findcoach.services.EventService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/coach/{coachAlias}/calendar")
public class CoachCalendarController {

    @Autowired
    private CoachService coachService;

    @Autowired
    private EventService eventService;


    @Autowired
    private EventConverterService eventConverterService;


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
                                         @DateTimeFormat(pattern = DateTimeDeserializer.DATE_TIME_PATTERN) LocalDateTime startDate,
                                         @RequestParam("endDate")
                                         @DateTimeFormat(pattern = DateTimeDeserializer.DATE_TIME_PATTERN) LocalDateTime endDate) {
        List<Event> events = eventService.findEventByDateRange(coachAlias, startDate, endDate);

        List<CalendarEvent> calendarEvents = eventConverterService.convertEventsToCalendarEvent(events);

        CalendarResponse response = new CalendarResponse();
        response.setEvents(calendarEvents);
        return response;
    }
}
