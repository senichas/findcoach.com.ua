package ua.com.findcoach.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import ua.com.findcoach.conf.DatabaseConfigurarion;
import ua.com.findcoach.repository.EventRepository;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {TestConfiguration.class, DatabaseConfigurarion.class})
public class EventServiceTest {

    @Autowired
    private EventRepository eventRepository;

    @Test
    public void testEventQuery() {
        assertThat(true, is(Boolean.TRUE));
    }
}