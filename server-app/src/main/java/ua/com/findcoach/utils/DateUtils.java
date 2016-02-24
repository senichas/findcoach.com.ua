package ua.com.findcoach.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtils {
    public static final Locale LOCALE_UKRAINE = new Locale("uk", "UA");
    private static final TemporalField FIELD_DAY_OF_WEEK = WeekFields.of(LOCALE_UKRAINE).dayOfWeek();

    public static LocalDateTime calculateDayEarliestTime(String date) {
        LocalDate lDate = stringToLocalDate(date, "M/dd/yyyy");
        return lDate.atTime(0, 0, 0);
    }

    public static LocalDateTime calculateDayLatestTime(String date) {
        LocalDate lDate = stringToLocalDate(date, "M/dd/yyyy");
        return lDate.atTime(23, 59, 59);
    }

    public static LocalDateTime calculateFirstDayOfWeekEarliestTime(String date) {
        LocalDate lDate = stringToLocalDate(date, "M/dd/yyyy");
        lDate = lDate.with(FIELD_DAY_OF_WEEK, 1);
        return lDate.atTime(0, 0, 0);
    }

    public static LocalDateTime calculateLastDayOfWeekLatestTime(String date) {
        LocalDate lDate = stringToLocalDate(date, "M/dd/yyyy");
        lDate = lDate.with(FIELD_DAY_OF_WEEK, 7);
        return lDate.atTime(23, 59, 59);
    }

    public static LocalDateTime calculateFirstDayOfMonthEarliestTime(String date) {
        LocalDate lDate = stringToLocalDate(date, "M/dd/yyyy");
        lDate = lDate.withDayOfMonth(1);
        return lDate.atTime(0, 0, 0);
    }

    public static LocalDateTime calculateLastDayOfMonthLatestTime(String date) {
        LocalDate lDate = stringToLocalDate(date, "M/dd/yyyy");
        lDate = lDate.withDayOfMonth(lDate.lengthOfMonth());
        return lDate.atTime(23, 59, 59);
    }

    public static LocalDate longToLocalDate(Long longDate) {
        Instant dateInstant = Instant.ofEpochMilli(longDate);
        LocalDateTime dateTime = LocalDateTime.ofInstant(dateInstant, TimeZone.getDefault().toZoneId());
        return dateTime.toLocalDate();
    }

    public static LocalDate dateToLocalDate(Date date) {
        return longToLocalDate(date.getTime());
    }

    public static LocalDateTime stringToLocalDateTime(String date, String pattern) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(date, format);
    }

    public static LocalDate stringToLocalDate(String date, String pattern) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern(pattern);
        return LocalDate.parse(date, format);
    }

    @Deprecated
    public static LocalDate calculateFirstDayOfCurrentWeek() {
        LocalDate cureentDate = LocalDate.now();
        return cureentDate.with(FIELD_DAY_OF_WEEK, 1);
    }

    @Deprecated
    public static LocalDate calculateLastDayOfCurrentWeek() {
        LocalDate cureentDate = LocalDate.now();
        return cureentDate.with(FIELD_DAY_OF_WEEK, 7);
    }
}
