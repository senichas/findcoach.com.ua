package ua.com.findcoach.services;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ua.com.findcoach.api.CalendarEvent;
import ua.com.findcoach.domain.Event;
import ua.com.findcoach.domain.EventRecurrence;

@Service
public class ConverterService {

    private Function<EventRecurrence, CalendarEvent> eventRecurrenceToCalendarEventFunction = eventRecurrence -> {
        CalendarEvent calendarEvent = new CalendarEvent();
        calendarEvent.setStartTime(eventRecurrence.getStartDate());
        calendarEvent.setEndTime(eventRecurrence.getEndDate());
        calendarEvent.setAllDayEvent(eventRecurrence.getAllDay());
        calendarEvent.setId(eventRecurrence.getEventRecurrenceId());
        calendarEvent.setCrossDay(0);
        calendarEvent.setRecurringEvent(0);
        calendarEvent.setColor(4);
        calendarEvent.setEditable(0);
        calendarEvent.setAttends("");
        return calendarEvent;
    };

    private Function<Event, List<CalendarEvent>> eventToCalendarEventFunction = event -> {
        List<CalendarEvent> calendarEvents = event.getRecurrences()
                .stream()
                .map(eventRecurrenceToCalendarEventFunction)
                .collect(Collectors.toList());
        calendarEvents.stream().forEach(calendarEvent -> {
            calendarEvent.setSubject(event.getTitle());
            calendarEvent.setLocation(event.getLocation());
        });
        return calendarEvents;
    };

    public List<CalendarEvent> convertEventToCalendarEvent(Event event) {
        return eventToCalendarEventFunction.apply(event);
    }

    public List<CalendarEvent> convertEventToCalendarEvent(List<Event> events) {
        List<CalendarEvent> calendarEvents = new ArrayList<>();
        events.stream()
                .forEach(event -> calendarEvents.addAll(convertEventToCalendarEvent(event)));

        return calendarEvents;
    }

}
