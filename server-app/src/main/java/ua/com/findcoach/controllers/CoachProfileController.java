package ua.com.findcoach.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.com.findcoach.api.CalendarResponse;
import ua.com.findcoach.domain.Coach;
import ua.com.findcoach.i18n.LocalizedMessageResolver;
import ua.com.findcoach.services.CoachService;
import ua.com.findcoach.services.ConverterService;
import ua.com.findcoach.services.EventService;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


@RestController
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

    @RequestMapping("/dashboard.html")
    public ModelAndView coachDashboard() throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("message", messageResolver.getMessage("titlepage.welcome.coach"));
        params.put("coachAlias", coachService.retrieveCurrentCoach().getAlias());
        return new ModelAndView("coachDashboard", params);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/calendar.html")
    public ModelAndView coachCalendarPage() {
        Map<String, Object> paramerters = new HashMap<>();
        paramerters.put("coachAlias", coachService.retrieveCurrentCoach().getAlias());

        return new ModelAndView("padawan-management/coachCalendarPage", paramerters);
    }

    /**
     * TODO - refactor JS and code to use methods POST, GET, PUT, DELETE instead request parameter method
     */
    @RequestMapping(method = RequestMethod.POST, value = "/calendar")
    @ResponseBody
    public CalendarResponse coachCalendarHandler(@RequestParam("method") String method) {

       /* List<Event> events = eventService.findEventsForLoggedInUser();
        List<CalendarEvent> calendarEvents = converterService.convertEventToCalendarEvent(events);*/

        CalendarResponse response = new CalendarResponse();
//        response.setEvents(calendarEvents);
        return response;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/profile.html")
    public ModelAndView coachProfilePage() {
        return new ModelAndView("profile");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/coachProfileAttributes")
    public Map<String, String> receiveCoachProfileAttributes() {
        Map<String, String> coachAtributes = new HashMap<>();
        Coach coachAttribut = coachService.receiveCoachProfileAttributes();
        coachAtributes.put("alias", coachAttribut.getAlias());
        coachAtributes.put("title", coachAttribut.getTitle());
        coachAtributes.put("description", coachAttribut.getDescription());
        return coachAtributes;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/cv")
    public HttpStatus updateCoachProfileAttributes(@RequestBody LinkedHashMap body) {
        LinkedHashMap<String, String> coachProfileAttributes = body;
        coachService.updateCoachProfileAttributes(coachProfileAttributes.get("alias"),
                coachProfileAttributes.get("title"),
                coachProfileAttributes.get("description"));
        return HttpStatus.OK;
    }

}
