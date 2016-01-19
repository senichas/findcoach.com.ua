package ua.com.findcoach.api;

import ua.com.findcoach.domain.Goal;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ProgramDto {
    private String programName;
    private Goal goal;
    private Integer programId;
    private LocalDate programStartDate;
    private LocalDate programFinishDate;

    public ProgramDto(String programName, Goal goal, Integer programId, Date programStartDate, Date programFinishDate) {
        this.programName = programName;
        this.goal = goal;
        this.programId = programId;
        this.programStartDate = programStartDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        this.programFinishDate = programFinishDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public LocalDate getProgramStartDate() {
        return programStartDate;
    }

    public void setProgramStartDate(Date programStartDate) {
        this.programStartDate = programStartDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public LocalDate getProgramFinishDate() {
        return programFinishDate;
    }

    public void setProgramFinishDate(Date programFinishDate) {
        this.programFinishDate = programFinishDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public Integer getProgramId() {
        return programId;
    }

    public void setProgramId(Integer programId) {
        this.programId = programId;
    }
}
