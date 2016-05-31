package ua.com.findcoach.services;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import ua.com.findcoach.conf.DatabaseConfigurarion;
import ua.com.findcoach.domain.Event;
import ua.com.findcoach.repository.EventRepository;
import ua.com.findcoach.utils.DateUtils;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {TestConfiguration.class, DatabaseConfigurarion.class})
public class EventServiceTest {

    @Autowired
    private EventRepository eventRepository;

    @Test
    //TO DO: rewrite after get events functionality will be ready
    public void testEventQuery() {
        LocalTime begin = LocalTime.of(0, 0, 0);
        LocalTime end = LocalTime.of(23, 59, 59);
        LocalDateTime beginOfTheCurrentWeek = LocalDateTime.of(DateUtils.calculateFirstDayOfCurrentWeek(), begin);
        LocalDateTime endDayOfTheCurrentWeek = LocalDateTime.of(DateUtils.calculateLastDayOfCurrentWeek(), end);

        List<Event> events = eventRepository.findEventsInPeriodForCoach(beginOfTheCurrentWeek, endDayOfTheCurrentWeek, "vasa_petrovich");
        assertThat(true, is(Boolean.TRUE));
    }
}
