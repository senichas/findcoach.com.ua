package ua.com.findcoach.api;

import ua.com.findcoach.domain.Goal;

public class ProgramDto {
    private String programName;
    private Goal goal;
    private String localizedGoal;
    private Integer programId;

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

    public String getLocalizedGoal() {
        return localizedGoal;
    }

    public void setLocalizedGoal(String localizedGoal) {
        this.localizedGoal = localizedGoal;
    }
}
