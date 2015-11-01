package ua.com.findcoach.api;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class CalendarEventSerializer extends JsonSerializer<CalendarEvent> {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/d/yyyy HH:mm");
    @Override
    public void serialize(CalendarEvent calendarEvent, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartArray(11);

        jsonGenerator.writeNumber(calendarEvent.getId());
        jsonGenerator.writeString(calendarEvent.getSubject());
        jsonGenerator.writeString(calendarEvent.getStartTime().format(formatter));
        jsonGenerator.writeString(calendarEvent.getEndTime().format(formatter));
        jsonGenerator.writeNumber((calendarEvent.getAllDayEvent()?1:0));
        jsonGenerator.writeNumber(calendarEvent.getCrossDay());
        jsonGenerator.writeNumber(calendarEvent.getRecurringEvent());
        jsonGenerator.writeNumber(calendarEvent.getColor());
        jsonGenerator.writeNumber(calendarEvent.getEditable());
        jsonGenerator.writeString(calendarEvent.getLocation());
        jsonGenerator.writeString(calendarEvent.getAttends());

        jsonGenerator.writeEndArray();
    }
}
