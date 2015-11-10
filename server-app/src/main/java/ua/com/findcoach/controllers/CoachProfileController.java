package ua.com.findcoach.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ua.com.findcoach.api.CalendarEvent;
import ua.com.findcoach.api.CalendarResponse;
import ua.com.findcoach.domain.CoachStatus;
import ua.com.findcoach.domain.Event;
import ua.com.findcoach.exception.StatusUpdateException;
import ua.com.findcoach.i18n.LocalizedMessageResolver;
import ua.com.findcoach.services.CoachService;
import ua.com.findcoach.services.ConverterService;
import ua.com.findcoach.services.EventService;

@Controller
@RequestMapping("/coach/profile")
public class CoachProfileController {

    @Autowired
    private LocalizedMessageResolver messageResolver;

    @Autowired
    private CoachService coachService;

    @Autowired
    private EventService eventService;

    @Autowired
    private ConverterService converterService;

    private static final int SINGLE_ROW = 1;


    @RequestMapping("/dashboard.html")
    public ModelAndView coachDashboard() throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("message", messageResolver.getMessage("titlepage.welcome.coach"));
        params.put("coachAlias", coachService.retrieveCurrentCoach().getAlias());
        return new ModelAndView("coachDashboard", params);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/status")
    @ResponseBody
    public HttpStatus updateCoachStatus(@RequestParam("status") String status) throws IOException, StatusUpdateException {
        int updatedRowCount = coachService.updateStatus(CoachStatus.valueOf(status));
        if (updatedRowCount == SINGLE_ROW) {
            return HttpStatus.OK;
        }
        throw new StatusUpdateException("Something was going wrong");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/calendar.html")
    public ModelAndView coachCalendarPage() {
        return new ModelAndView("coachCalendarPage");
    }

    /**
     * TODO - refactor JS and code to use methods POST, GET, PUT, DELETE instead request parameter method
     */
    @RequestMapping(method = RequestMethod.POST, value = "/calendar")
    @ResponseBody
    public CalendarResponse coachCalendarHandler(@RequestParam("method") String method) {

        List<Event> events = eventService.findEventsForLoggedInUser();
        List<CalendarEvent> calendarEvents = converterService.convertEventToCalendarEvent(events);

        CalendarResponse response = new CalendarResponse();
        response.setEvents(calendarEvents);
        return response;
    }


}
