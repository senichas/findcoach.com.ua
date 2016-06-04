package ua.com.findcoach.api;

import ua.com.findcoach.domain.Goal;

import java.util.List;

public class ProgramDetailsDto {
    private Integer programId;
    private String programName;
    private Goal goal;
    private String coachAlias;
    private List<CycleDto> cycleDtos;

    public Integer getProgramId() {
        return programId;
    }

    public void setProgramId(Integer programId) {
        this.programId = programId;
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

    public String getCoachAlias() {
        return coachAlias;
    }

    public void setCoachAlias(String coachAlias) {
        this.coachAlias = coachAlias;
    }

    public List<CycleDto> getCycleDtos() {
        return cycleDtos;
    }

    public void setCycleDtos(List<CycleDto> cycleDtos) {
        this.cycleDtos = cycleDtos;
    }
}
