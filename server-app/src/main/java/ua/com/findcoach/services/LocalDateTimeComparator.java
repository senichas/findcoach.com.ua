package ua.com.findcoach.services;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Comparator;

@Component
public class LocalDateTimeComparator implements Comparator<LocalDateTime> {
    @Override
    public int compare(LocalDateTime date1, LocalDateTime date2) {
        return date1.compareTo(date2);
    }
}
