package ua.com.findcoach.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.findcoach.api.CycleDto;
import ua.com.findcoach.api.TrainingDto;
import ua.com.findcoach.domain.Cycle;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CycleConverterService {

    @Autowired
    private EventConverterService eventConverterService;

    @Autowired
    private Comparator<LocalDateTime> localDateTimeComparator;

    public CycleDto convertCycleToDto(Cycle cycle) {
        CycleDto cycleDto = new CycleDto();
        cycleDto.setCycleId(cycle.getCycleId());
        cycleDto.setName(cycle.getName());
        cycleDto.setNotes(cycle.getNotes());

        Comparator<TrainingDto> trainingDtoComparator = (trainingDto1, trainingDto2) -> trainingDto1.getStartDateTime().compareTo(trainingDto2.getStartDateTime());

        List<TrainingDto> trainingDtosCollected = cycle.getEvents().stream()
                .map(event -> eventConverterService.convertEventToTrainings(event))
                .flatMap(trainingDtos -> trainingDtos.stream())
                .sorted(trainingDtoComparator)
                .collect(Collectors.toList());

        LocalDateTime minTrainingDate = null;
        LocalDateTime maxTrainingDate = null;

        if (trainingDtosCollected.size() > 0) {
            minTrainingDate = trainingDtosCollected.stream()
                    .map(trainingDto -> trainingDto.getStartDateTime())
                    .min(localDateTimeComparator)
                    .get();

            maxTrainingDate = trainingDtosCollected.stream()
                    .map(trainingDto -> trainingDto.getStartDateTime())
                    .max(localDateTimeComparator)
                    .get();
        }

        cycleDto.setStartDate(minTrainingDate);
        cycleDto.setEndDate(maxTrainingDate);

        cycleDto.setTrainings(trainingDtosCollected);

        return cycleDto;
    }

    public List<CycleDto> convertCyclesListToDtos(List<Cycle> cycles) {
        List<CycleDto> cycleDtos = cycles.stream().map(cycle -> convertCycleToDto(cycle)).collect(Collectors.toList());
        return cycleDtos;
    }
}
