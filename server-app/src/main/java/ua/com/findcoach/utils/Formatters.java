package ua.com.findcoach.utils;

import java.time.format.DateTimeFormatter;

public class Formatters {
    private Formatters() {
    }

    public static final DateTimeFormatter SIMPLE_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
}
