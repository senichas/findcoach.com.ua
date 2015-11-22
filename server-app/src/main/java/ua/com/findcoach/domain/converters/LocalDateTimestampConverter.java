package ua.com.findcoach.domain.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDate;

@Converter(autoApply = true)
public class LocalDateTimestampConverter implements AttributeConverter<LocalDate, Timestamp> {
    @Override
    public Timestamp convertToDatabaseColumn(LocalDate localDate) {
        return (localDate == null ? null : Timestamp.valueOf(localDate.atStartOfDay()));

    }

    @Override
    public LocalDate convertToEntityAttribute(Timestamp timestamp) {
        return timestamp.toLocalDateTime().toLocalDate();
    }
}
