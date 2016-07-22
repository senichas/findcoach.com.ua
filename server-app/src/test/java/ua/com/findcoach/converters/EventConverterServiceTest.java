package ua.com.findcoach.converters;

import org.junit.Before;
import org.junit.Test;
import ua.com.findcoach.api.CalendarEvent;
import ua.com.findcoach.domain.Event;
import ua.com.findcoach.domain.EventRecurrence;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class EventConverterServiceTest {
    private EventConverterService service;

    @Before
    public void setUp() throws Exception {
        service = new EventConverterService();
    }

    @Test
    public void convertEventToCalendarEvent() {
        Event event = new Event();
        event.setTitle("Test event");
        event.setRecurrences(new ArrayList<>());

        EventRecurrence recurrence1 = new EventRecurrence();
        recurrence1.setStartDate(LocalDateTime.of(2015, 11, 2, 15, 0));
        recurrence1.setEndDate(LocalDateTime.of(2015, 11, 2, 17, 0));
        event.getRecurrences().add(recurrence1);

        EventRecurrence recurrence2 = new EventRecurrence();
        recurrence2.setStartDate(LocalDateTime.of(2015, 11, 3, 15, 0));
        recurrence2.setEndDate(LocalDateTime.of(2015, 11, 3, 17, 0));
        event.getRecurrences().add(recurrence2);

        List<CalendarEvent> calendarEvents = service.convertEventToCalendarEvent(event);

        assertThat(calendarEvents.size(), equalTo(2));

    }

}