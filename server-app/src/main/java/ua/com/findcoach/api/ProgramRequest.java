package ua.com.findcoach.api;

import ua.com.findcoach.domain.Goal;

public class ProgramRequest {
    private Integer programId;
    private String name;
    private Goal goal;

    public Integer getProgramId() {
        return programId;
    }

    public void setProgramId(Integer programId) {
        this.programId = programId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }
}
