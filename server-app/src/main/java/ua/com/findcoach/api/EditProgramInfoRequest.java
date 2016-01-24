package ua.com.findcoach.api;

import org.hibernate.validator.constraints.NotEmpty;
import ua.com.findcoach.domain.Goal;

import java.util.Date;

public class EditProgramInfoRequest {
    @NotEmpty(message = "Write the name. please")
    private String name;
    private Goal goal;
    private Date start;
    private Date finish;

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

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getFinish() {
        return finish;
    }

    public void setFinish(Date finish) {
        this.finish = finish;
    }
}
