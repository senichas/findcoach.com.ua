package ua.com.findcoach.services;

import org.springframework.stereotype.Service;
import ua.com.findcoach.api.CalendarEvent;
import ua.com.findcoach.api.PadawanDto;
import ua.com.findcoach.api.ProgramDto;
import ua.com.findcoach.domain.Event;
import ua.com.findcoach.domain.EventRecurrence;
import ua.com.findcoach.domain.Padawan;
import ua.com.findcoach.domain.Program;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    public ProgramDto convertProgramToDto(Program program) {
        ProgramDto dto = new ProgramDto();
        dto.setProgramId(program.getProgramId());
        dto.setGoal(program.getGoal());
        dto.setProgramName(program.getName());
        dto.setProgramStartDate(program.getStartDate());
        dto.setProgramFinishDate(program.getEndDate());

        return dto;
    }

    public List<ProgramDto> convertProgramsToDtos(List<Program> programs) {
        return programs.stream().map(program -> convertProgramToDto(program)).collect(Collectors.toList());
    }

    public PadawanDto convertPadawanToDto(Padawan padawan) {
        PadawanDto dto = new PadawanDto();
        dto.setPadawanId(padawan.getPadawanId());
        dto.setPadawanActive(padawan.isActive());
        dto.setFirstName(padawan.getFirstName());
        dto.setLastName(padawan.getLastName());
        dto.setBirthday(padawan.getBirthday());
        dto.setEmail(padawan.getEmail());
        dto.setGender(padawan.getGender());
        dto.setNotes(padawan.getNotes());

        dto.setProgramDtos(convertProgramsToDtos(padawan.getProgramList()));

        return dto;
    }

    public List<PadawanDto> convertPadawansToDtos(List<Padawan> padawans) {
        return padawans.stream().map(padawan -> convertPadawanToDto(padawan)).collect(Collectors.toList());
    }
}
