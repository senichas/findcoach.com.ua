package ua.com.findcoach.api.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateDeserializer extends JsonDeserializer<LocalDate> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateTimeDeserializer.DATE_TIME_PATTERN);

    @Override
    public LocalDate deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String dateValue = parser.getText();
        return LocalDate.parse(dateValue, formatter);
    }
}
