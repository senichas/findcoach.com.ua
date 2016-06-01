package ua.com.findcoach.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.findcoach.api.EventDto;
import ua.com.findcoach.domain.Event;
import ua.com.findcoach.domain.EventType;
import ua.com.findcoach.i18n.LocalizedMessageResolver;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class EventConverterService {
    @Autowired
    private LocalizedMessageResolver messageResolver;

    @Resource
    private Map<EventType, String> eventTypeLocalizationKeys;

    public EventDto convertEventToDto(Event event) {
        EventDto eventDto = new EventDto();
        eventDto.setEventId(event.getEventId());
        eventDto.setTitle(event.getTitle());
        eventDto.setDescription(event.getDescription());
        eventDto.setLocation(event.getLocation());
        String key = eventTypeLocalizationKeys.get(event.getType());
        String msg = messageResolver.getMessage(key);
        eventDto.setTypeLocalized(msg);

        return eventDto;
    }
}
