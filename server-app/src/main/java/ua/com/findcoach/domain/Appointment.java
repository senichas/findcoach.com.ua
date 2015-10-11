package ua.com.findcoach.domain;

import java.util.List;

/**
 * Created by senich on 10/9/2015.
 */
public class Appointment {
    private Long appointmentId;

    private String title;

    private String summary;

    List<AppointmentRecurrence> recurrences;
}
