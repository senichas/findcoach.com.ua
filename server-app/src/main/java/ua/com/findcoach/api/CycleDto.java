package ua.com.findcoach.api;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ua.com.findcoach.api.serializers.DateTimeSerializer;

import java.time.LocalDateTime;
import java.util.List;

public class CycleDto {
    private Integer cycleId;
    private String name;
    @JsonSerialize(using = DateTimeSerializer.class)
    private LocalDateTime startDate;
    @JsonSerialize(using = DateTimeSerializer.class)
    private LocalDateTime endDate;
    private String notes;

    private List<TrainingDto> trainings;

    public Integer getCycleId() {
        return cycleId;
    }

    public void setCycleId(Integer cycleId) {
        this.cycleId = cycleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<TrainingDto> getTrainings() {
        return trainings;
    }

    public void setTrainings(List<TrainingDto> trainings) {
        this.trainings = trainings;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}
