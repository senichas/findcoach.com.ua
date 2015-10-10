package ua.com.findcoach.domain;

/**
 * Created by DENIS on 10.10.2015.
 */
public enum CoachStatus {
    L("L");

    private final String name;

    private CoachStatus(String s) {
        name = s;
    }

    public String getName() {
        return name;
    }
}
