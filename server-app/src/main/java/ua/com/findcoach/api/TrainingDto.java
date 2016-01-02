package ua.com.findcoach.api;

import javax.validation.constraints.Pattern;

public class TrainingDto {

    @Pattern(regexp = "((19|20)[0-9]{2})([-])(0[1-9]|1[012])([-])(0[1-9]|[12][0-9]|3[01]) ([01][0-9]|2[0-3]):([0-5][0-9])")
    private String startDateTime;

    private Integer duration;

    private String content;

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
