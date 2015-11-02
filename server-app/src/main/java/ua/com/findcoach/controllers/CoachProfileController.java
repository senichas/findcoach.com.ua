package ua.com.findcoach.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ua.com.findcoach.domain.CoachStatus;
import ua.com.findcoach.exception.StatusUpdateException;
import ua.com.findcoach.i18n.LocalizedMessageResolver;
import ua.com.findcoach.services.CoachService;
import ua.com.findcoach.utils.CoachStatusHolder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/coach/profile")
public class CoachProfileController {

    @Autowired
    private LocalizedMessageResolver messageResolver;

    @Autowired
    private CoachService coachService;

    private static final int SINGLE_ROW = 1;


    @RequestMapping("/home.html")
    public ModelAndView coachHomePage() throws IOException {
        Map<String, Object> params = new HashMap<>();
        Map<Enum, String> statusMap = new HashMap<>();
        Map<Enum, String> statuses = CoachStatusHolder.getStatusMap();

        statuses
                .entrySet()
                .stream()
                .forEach(enumStringEntry ->
                                statusMap.put(enumStringEntry.getKey(), messageResolver.getMessage(enumStringEntry.getValue()))
                );


        params.put("message", messageResolver.getMessage("titlepage.welcome.coach"));
        params.put("status", statusMap);

        return new ModelAndView("coachHome", params);
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

}
