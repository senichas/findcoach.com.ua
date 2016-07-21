package ua.com.findcoach.services;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TermServiceTest {

    private TermService termService;
    @Before
    public void setUp() throws Exception {
        termService = new TermService();
    }

    @Test
    public void calculateEndDateCorrectlyForWeekTerms() throws Exception {
        LocalDateTime startDate = LocalDateTime.of(2016, 6, 25, 13, 0);
        String term = "3weeks";
        LocalDateTime expectedEndDate = LocalDateTime.of(2016, 7, 16, 13, 0);
        assertThat(termService.findEndDateByTerm(startDate, term), equalTo(expectedEndDate));

    }
}