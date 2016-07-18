package ua.com.findcoach.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.com.findcoach.api.CalendarEvent;
import ua.com.findcoach.api.CalendarResponse;
import ua.com.findcoach.domain.Coach;
import ua.com.findcoach.domain.ViewDateRange;
import ua.com.findcoach.services.CoachService;
import ua.com.findcoach.services.EventService;
import ua.com.findcoach.utils.DateUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/coach/{coachAlias}/calendar")
public class CoachCalendarController {

    @Autowired
    private CoachService coachService;

    @Autowired
    private EventService eventService;


    @RequestMapping(method = RequestMethod.GET, value = "/dashboard.html")
    public ModelAndView coachCalendarPage() {
        Map<String, Object> paramerters = new HashMap<>();
        paramerters.put("coachAlias", coachService.retrieveCurrentCoach().getAlias());

        return new ModelAndView("padawan-management/coachCalendarPage", paramerters);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/events")
    public
    @ResponseBody
    CalendarResponse fetchEventsForCoach(@PathVariable("coachAlias") String coachUserName, @RequestParam("showdate") String showDate,
                                                                                           @RequestParam("viewtype") ViewDateRange viewType,
                                                                                           @RequestParam("timezone") String timeZone) {
        Coach currentCoach = coachService.retrieveCurrentCoach();

        LocalDateTime startTimeToShow;
        LocalDateTime endTimeToShow;

        if (viewType.getDateRange().equals(ViewDateRange.week.getDateRange())) {
            startTimeToShow = DateUtils.calculateFirstDayOfWeekEarliestTime(showDate);
            endTimeToShow = DateUtils.calculateLastDayOfWeekLatestTime(showDate);
        } else if (viewType.getDateRange().equals(ViewDateRange.month.getDateRange())){
            startTimeToShow = DateUtils.calculateFirstDayOfMonthEarliestTime(showDate);
            endTimeToShow = DateUtils.calculateLastDayOfMonthLatestTime(showDate);
        } else {
            startTimeToShow = DateUtils.calculateDayEarliestTime(showDate);
            endTimeToShow = DateUtils.calculateDayLatestTime(showDate);
        }

        CalendarResponse response = new CalendarResponse();
        response.setStart(startTimeToShow);
        response.setEnd(endTimeToShow);
        response.setIssort(Boolean.TRUE);

        List<CalendarEvent> events = new ArrayList<>();

        CalendarEvent event1 = new CalendarEvent();
        event1.setId(1);
        event1.setSubject("TRX Training");
        event1.setStartTime(LocalDateTime.of(2016, 2, 18, 7, 0));
        event1.setEndTime(LocalDateTime.of(2016, 2, 18, 10, 0));
        event1.setAllDayEvent(Boolean.FALSE);
        event1.setCrossDay(0);
        event1.setRecurringEvent(0);
        event1.setColor(2);
        event1.setEditable(0);
        event1.setLocation("Start gym");
        event1.setAttends("");

        events.add(event1);

        response.setEvents(events);
        return response;
    }
}
