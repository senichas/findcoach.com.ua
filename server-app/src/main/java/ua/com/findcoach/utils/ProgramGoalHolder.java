package ua.com.findcoach.utils;

import ua.com.findcoach.domain.Goal;

import java.util.ArrayList;
import java.util.List;

public class ProgramGoalHolder {
    private final static List<Enum> PROGRAM_GOAL_HOLDER = new ArrayList<>();

    static {
        for (Goal goal : Goal.values()){
            PROGRAM_GOAL_HOLDER.add(goal);
        }
    }

    public static List<Enum> getGoalList(){
        return PROGRAM_GOAL_HOLDER;
    }
}
