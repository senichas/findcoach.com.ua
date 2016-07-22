package ua.com.findcoach.utils;

import org.junit.Test;

import java.time.DayOfWeek;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DateUtilsTest {

    @Test
    public void parseStringToDayOfWeek() throws Exception {
        assertThat(DateUtils.convertStringToDayOfWeek("Monday"), equalTo(DayOfWeek.MONDAY));
        assertThat(DateUtils.convertStringToDayOfWeek("monday"), equalTo(DayOfWeek.MONDAY));
        assertThat(DateUtils.convertStringToDayOfWeek("Fri"), equalTo(DayOfWeek.FRIDAY));


    }
}
