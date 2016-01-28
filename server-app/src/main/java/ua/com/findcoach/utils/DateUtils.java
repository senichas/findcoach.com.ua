package ua.com.findcoach.utils;

import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;

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
}
