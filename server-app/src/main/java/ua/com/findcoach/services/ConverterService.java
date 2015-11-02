package ua.com.findcoach.services;

import ua.com.findcoach.api.CalendarEvent;
import ua.com.findcoach.domain.Event;
import ua.com.findcoach.domain.EventRecurrence;
import ua.com.findcoach.utils.CustomListCollector;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ConverterService {
    public List<CalendarEvent> convertEventToCalendarEvent(List<Event> events) {
        final List<CalendarEvent> calendarEvents = new ArrayList<>();
        events.stream().forEach(event ->
            event.getRecurrences().stream().collect(new ()) {

        }

        );

        return calendarEvents;

        /*eventRecurrence -> {
                CalendarEvent calendarEvent = new CalendarEvent();
                c\alendarEvent.setSubject(event.getTitle());
                calendarEvent.setLocation(event.getLocation());
                calendarEvent.setAllDayEvent(eventRecurrence.getAllDay());
                calendarEvent.setRecurringEvent(0);
                calendarEvent.setStartTime(eventRecurrence.getStartDate());
                calendarEvent.setEndTime(eventRecurrence.getEndDate());

            }).*/
    }

    private static class EventRecurrenceToCalendarEventCollector extends CustomListCollector<EventRecurrence, CalendarEvent> {
        @Override
        public Function<List<EventRecurrence>, CalendarEvent> finisher() {
            (eventRecurrence, calendarEvent)
            return ;
        }
    }
}
