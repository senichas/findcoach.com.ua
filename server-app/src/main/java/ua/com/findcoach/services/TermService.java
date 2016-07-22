package ua.com.findcoach.services;

import org.springframework.stereotype.Service;
import ua.com.findcoach.utils.TermParser;

import java.time.LocalDateTime;

@Service
public class TermService {

    public LocalDateTime findEndDateByTerm(LocalDateTime startDate, String term) {
        Integer termDaysCount = TermParser.parseTerm(term);
        LocalDateTime endDate = startDate.plusDays(termDaysCount);
        return endDate;
    }


}
