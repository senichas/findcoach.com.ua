package ua.com.findcoach.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.findcoach.domain.Coach;
import ua.com.findcoach.domain.Event;
import ua.com.findcoach.domain.EventRecurrence;
import ua.com.findcoach.repository.EventRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Event save(Event event) {
        eventRepository.save(event);
        return event;
    }

    public List<EventRecurrence> findEventRecurrences(Coach coach, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        List<EventRecurrence> recurrences = new ArrayList<>();

        return recurrences;
    }

//    public List<Event> findEventsForUserByUserName(String userName) {
//        return eventRepository.findAllByAlias(userName);
//    }

//    public List<Event> findEventsForLoggedInUser() {
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        return eventRepository.findAllByAlias(user.getAlias());
//    }


}
