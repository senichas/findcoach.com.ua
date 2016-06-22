package ua.com.findcoach.api;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import ua.com.findcoach.api.deserializers.DateTimeDeserializer;

import java.time.LocalDateTime;
import java.util.List;

public class AddNewTrainingRequest {
    private String description;
    private Integer duration;
    private Boolean repeat;
    private List<String> repeatOnDays;
    private String repeatTerm;

    @JsonDeserialize(using = DateTimeDeserializer.class)
    private LocalDateTime startDate;

    private String title;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Boolean getRepeat() {
        return repeat;
    }

    public void setRepeat(Boolean repeat) {
        this.repeat = repeat;
    }

    public List<String> getRepeatOnDays() {
        return repeatOnDays;
    }

    public void setRepeatOnDays(List<String> repeatOnDays) {
        this.repeatOnDays = repeatOnDays;
    }

    public String getRepeatTerm() {
        return repeatTerm;
    }

    public void setRepeatTerm(String repeatTerm) {
        this.repeatTerm = repeatTerm;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
