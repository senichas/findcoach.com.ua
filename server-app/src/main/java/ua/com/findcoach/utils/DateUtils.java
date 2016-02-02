package ua.com.findcoach.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtils {
    public static final Locale LOCALE_UKRAINE = new Locale("uk", "UA");
    private static final TemporalField FIELD_DAY_OF_WEEK = WeekFields.of(LOCALE_UKRAINE).dayOfWeek();


    public static LocalDate calculateFirstDayOfCurrentWeek() {
        LocalDate cureentDate = LocalDate.now();
        return cureentDate.with(FIELD_DAY_OF_WEEK, 1);
    }

    public static LocalDate calculateLastDayOfCurrentWeek() {
        LocalDate cureentDate = LocalDate.now();
        return cureentDate.with(FIELD_DAY_OF_WEEK, 7);
    }

    public static LocalDate longToLocalDate(Long longDate) {
        Instant dateInstant = Instant.ofEpochMilli(longDate);
        LocalDateTime dateTime = LocalDateTime.ofInstant(dateInstant, TimeZone.getDefault().toZoneId());
        return dateTime.toLocalDate();
    }

    public static LocalDate dateToLocalDate(Date date) {
        return longToLocalDate(date.getTime());
    }
}
