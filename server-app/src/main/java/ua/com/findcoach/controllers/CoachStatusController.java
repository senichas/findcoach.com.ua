package ua.com.findcoach.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ua.com.findcoach.i18n.LocalizedMessageResolver;
import ua.com.findcoach.services.CoachService;
import ua.com.findcoach.utils.CoachStatusHolder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by DENIS on 07.11.2015.
 */
@RestController
@RequestMapping("/coach/profile")
public class CoachStatusController {
    @Autowired
    private LocalizedMessageResolver messageResolver;

    @Autowired
    private CoachService coachService;

    @RequestMapping(method = RequestMethod.GET, value = "/statuses")
    public Map<Enum, String> getCoachStatuses() {
        Map<Enum, String> statusMap = new HashMap<>();
        Map<Enum, String> statuses = CoachStatusHolder.getStatusMap();

        statuses
                .entrySet()
                .stream()
                .forEach(enumStringEntry ->
                                statusMap.put(enumStringEntry.getKey(), messageResolver.getMessage(enumStringEntry.getValue()))
                );
        return statusMap;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/status")
    public Enum getCurrentStatus(){
        return coachService.getCurrentCoachStatus();
    }
}
