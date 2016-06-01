package ua.com.findcoach.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.findcoach.api.EventDto;
import ua.com.findcoach.api.TrainingDto;
import ua.com.findcoach.domain.Event;
import ua.com.findcoach.domain.EventType;
import ua.com.findcoach.i18n.LocalizedMessageResolver;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public List<TrainingDto> convertEventToTrainings(Event event) {

        List<TrainingDto> trainingDtos = event.getRecurrences().stream().map(eventRecurrence -> {
            TrainingDto trainingDto = new TrainingDto();

            trainingDto.setEventId(event.getEventId());
            trainingDto.setEventRecurrenceId(eventRecurrence.getEventRecurrenceId());
            trainingDto.setTitle(event.getTitle());
            trainingDto.setDescription(event.getDescription());

            String key = eventTypeLocalizationKeys.get(event.getType());
            String msg = messageResolver.getMessage(key);
            trainingDto.setTypeLocalized(msg);
            trainingDto.setAllDay(eventRecurrence.getAllDay());
            trainingDto.setStartDateTime(eventRecurrence.getStartDate());
            trainingDto.setEndDateTime(eventRecurrence.getEndDate());

            return trainingDto;
        }).collect(Collectors.toList());

        return trainingDtos;
    }
}
