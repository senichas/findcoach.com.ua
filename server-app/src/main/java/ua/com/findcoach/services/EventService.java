package ua.com.findcoach.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.findcoach.domain.Coach;
import ua.com.findcoach.domain.Event;
import ua.com.findcoach.domain.EventRecurrence;
import ua.com.findcoach.domain.EventType;
import ua.com.findcoach.repository.EventRepository;
import ua.com.findcoach.utils.DateUtils;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {
    private static final Logger LOG = LoggerFactory.getLogger(EventService.class);


    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private TermService termService;

    public Event save(Event event) {
        eventRepository.save(event);
        return event;
    }

    public List<EventRecurrence> findEventRecurrences(Coach coach, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        List<EventRecurrence> recurrences = new ArrayList<>();
//        List<Event> events = eventRepository.findEventsInPeriodForCoach(startDateTime, endDateTime, coach.getAlias());
        return recurrences;
    }

    public Event composeNewEvent(String eventTitle, String eventDescription, List<String> repeatOnDays,
                             String repeatTerm, LocalDateTime startDate, Integer durationMin) {
        Event newEvent = new Event();
        newEvent.setTitle(eventTitle);
        newEvent.setDescription(eventDescription);
        newEvent.setType(EventType.TRAINING);
        // TODO - fill training location

        LocalDateTime endDate = termService.findEndDateByTerm(startDate, repeatTerm);

        LocalDateTime currentRecurrentDate = startDate;
        List<EventRecurrence> eventRecurrences = new ArrayList<>();
        List<DayOfWeek> repeatOnDaysWeek = DateUtils.convertStringsIntoDays(repeatOnDays);

        while (currentRecurrentDate.isBefore(endDate) || currentRecurrentDate.isEqual(endDate)) {

            DayOfWeek currentRecurrentDateDayOfWeek = currentRecurrentDate.getDayOfWeek();
            if (repeatOnDaysWeek.contains(currentRecurrentDateDayOfWeek)) {
                LOG.debug("Adding recurring event on " + currentRecurrentDate.toString());
                EventRecurrence eventRecurrence = new EventRecurrence();
                eventRecurrence.setEvent(newEvent);

                LocalDateTime recurrenceStartDate = currentRecurrentDate;
                LocalDateTime recurrenceEndDate = currentRecurrentDate.plusMinutes(durationMin);
                eventRecurrence.setStartDate(recurrenceStartDate);
                eventRecurrence.setEndDate(recurrenceEndDate);

                eventRecurrence.setAllDay(Boolean.FALSE);

                eventRecurrences.add(eventRecurrence);
            }
            currentRecurrentDate = currentRecurrentDate.plusDays(1);
        }

        newEvent.setRecurrences(eventRecurrences);
        return newEvent;
    }
}
