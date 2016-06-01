package ua.com.findcoach.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.findcoach.api.CycleDto;
import ua.com.findcoach.api.EventDto;
import ua.com.findcoach.domain.Cycle;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CycleConverterService {

    @Autowired
    private EventConverterService eventConverterService;

    public CycleDto convertCycleToDto(Cycle cycle) {
        CycleDto cycleDto = new CycleDto();
        cycleDto.setCycleId(cycle.getCycleId());
        cycleDto.setName(cycle.getName());
        cycleDto.setNotes(cycle.getNotes());

        List<EventDto> eventDtos = cycle.getEvents().stream().map(event -> eventConverterService.convertEventToDto(event)).collect(Collectors.toList());

        cycleDto.setEvents(eventDtos);

        return cycleDto;
    }

    public List<CycleDto> convertCyclesListToDtos(List<Cycle> cycles) {
        List<CycleDto> cycleDtos = cycles.stream().map(cycle -> convertCycleToDto(cycle)).collect(Collectors.toList());
        return cycleDtos;
    }
}
