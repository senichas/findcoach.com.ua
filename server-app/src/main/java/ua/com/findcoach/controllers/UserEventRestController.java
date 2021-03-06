package ua.com.findcoach.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.com.findcoach.api.CalendarEvent;
import ua.com.findcoach.converters.ConverterService;
import ua.com.findcoach.services.EventService;

import java.util.List;

@RequestMapping("/user/{userName}")
@RestController
public class UserEventRestController {

    @Autowired
    private EventService eventService;

    @Autowired
    private ConverterService converterService;

    @RequestMapping(method = RequestMethod.GET, value = "/events")
    public List<CalendarEvent> findEventsForUser(@PathVariable("userName") String userName) {
//        List<Event> events = eventService.findEventsForUserByUserName(userName);
//        List<CalendarEvent> calendarEvents = converterService.convertEventToCalendarEvent(events);
//        return calendarEvents;
        return null;
    }
}
