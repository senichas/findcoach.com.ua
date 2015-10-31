package ua.com.findcoach.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.com.findcoach.domain.CoachStatus;
import ua.com.findcoach.exception.StatusUpdateException;
import ua.com.findcoach.i18n.LocalizedMessageResolver;
import ua.com.findcoach.services.CoachService;
import ua.com.findcoach.utils.CoachStatusHolder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLDecoder;
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
        Enum currentStatus = coachService.getCurrentStatus();

        statuses
                .entrySet()
                .stream()
                .forEach(enumStringEntry ->
                                statusMap.put(enumStringEntry.getKey(), messageResolver.getMessage(enumStringEntry.getValue()))
                );


        params.put("message", messageResolver.getMessage("titlepage.welcome.coach"));
        params.put("status", statusMap);
        params.put("currentStatus", currentStatus);

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

    @RequestMapping("/coachCV.html")
    public ModelAndView coachCV(){
        return new ModelAndView("coachCV");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/cv")
    @ResponseBody
    public ModelAndView saveCoachCV(@RequestParam("alias") String requestAlias, @RequestParam("header") String requestHeader, @RequestParam("description") String requestDescription,  HttpServletRequest httpServletRequest) throws IOException, StatusUpdateException{
        int updatedRowCount = coachService.setCoachCV(requestAlias,requestHeader,requestDescription);
        if (updatedRowCount == SINGLE_ROW){
            return new ModelAndView("coachCV");
        }
        throw new StatusUpdateException("Can't set coach CV");
    }

}
