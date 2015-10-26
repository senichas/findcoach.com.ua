package ua.com.findcoach.domain;

import java.util.List;

/**
 * Created by senich on 10/9/2015.
 */
public class Event {
    private Long eventId;

    private String title;

    private String summary;

    private EventType eventType;

    private Integer color;

    private String location;

    List<EventRecurrence> recurrences;
}
