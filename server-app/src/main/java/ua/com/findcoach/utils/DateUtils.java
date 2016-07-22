package ua.com.findcoach.utils;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class DateUtils {
    public static final Locale LOCALE_UKRAINE = new Locale("uk", "UA");
    private static final TemporalField FIELD_DAY_OF_WEEK = WeekFields.of(LOCALE_UKRAINE).dayOfWeek();

    private static final SimpleDateFormat DAY_FORMAT = new SimpleDateFormat("E", Locale.UK);


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

    public static List<DayOfWeek> convertStringsIntoDays(List<String> days) {
        List<DayOfWeek> dayOfWeeks = days.stream().map(DateUtils::convertStringToDayOfWeek).collect(Collectors.toList());
        return dayOfWeeks;
    }

    public static DayOfWeek convertStringToDayOfWeek(String dayName) {
        String lowerDayName = dayName.toLowerCase();
        int dayOfWeekNumber;
        switch (lowerDayName) {
            case "mon":
            case "monday":
                dayOfWeekNumber = 1;
                break;
            case "tue":
            case "tuesday":
                dayOfWeekNumber = 2;
                break;
            case "wed":
            case "wednesday":
                dayOfWeekNumber = 3;
                break;
            case "thu":
            case "thursday":
                dayOfWeekNumber = 4;
                break;
            case "fri":
            case "friday":
                dayOfWeekNumber = 5;
                break;
            case "sat":
            case "saturday":
                dayOfWeekNumber = 6;
                break;
            case "sun":
            case "sunday":
                dayOfWeekNumber = 7;
                break;
            default:
                dayOfWeekNumber = -1;

        }
        if (dayOfWeekNumber > 0) {
            return DayOfWeek.of(dayOfWeekNumber);
        } else {
            throw new IllegalArgumentException("Day name is invalid");
        }

    }

}
