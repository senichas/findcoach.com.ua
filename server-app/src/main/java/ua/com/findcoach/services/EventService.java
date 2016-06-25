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
//        List<Event> events = eventRepository.findEventsInPeriodForCoach(startDateTime, endDateTime, coach.getAlias());
        return recurrences;
    }

    public Event addNewEvent(Integer cycleId, String eventTitle, String eventDescription, List<String> repeatOnDays,
                             String repeatTerm, LocalDateTime startDate) {
        Event newEvent = new Event();
        newEvent.setTitle(eventTitle);
        newEvent.setDescription(eventDescription);

        List<EventRecurrence> eventRecurrences = new ArrayList<>();

        EventRecurrence eventRecurrence = new EventRecurrence();

        return newEvent;

    }
}
