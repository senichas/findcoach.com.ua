package ua.com.findcoach.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.com.findcoach.domain.Event;
import ua.com.findcoach.services.EventService;

import java.util.List;

@RequestMapping("/user/{userName}")
@RestController
public class EventPublicController {

    @Autowired
    private EventService eventService;

    @RequestMapping(method = RequestMethod.GET, value = "/events")
    public List<Event> findEventsForUser(@PathVariable("userName") String userName) {
        List<Event> events = eventService.findEventsForUserByUserName(userName);
        return events;
    }
}