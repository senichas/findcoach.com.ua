package ua.com.findcoach.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.findcoach.domain.Event;
import ua.com.findcoach.repository.EventRepository;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<Event> findEventsForUserByUserName(String userName) {
        return eventRepository.findAllByAlias(userName);
    }
}
