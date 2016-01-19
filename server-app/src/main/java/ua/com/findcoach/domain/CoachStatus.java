package ua.com.findcoach.domain;


public enum CoachStatus {
    LOOKING_FOR_PADAWAN("looking_for_padawan"),
    ON_LEAVE("on_leave"),
    ON_SICK_LEAVE("on_sick_leave");


    private final String name;

    private CoachStatus(String s) {
        name = s;
    }

    public String getName() {
        return name;
    }
}
