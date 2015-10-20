package ua.com.findcoach.controllers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.com.findcoach.api.AuthenticationRequest;
import ua.com.findcoach.api.AuthentificationResponse;
import ua.com.findcoach.domain.CoachStatus;
import ua.com.findcoach.domain.PadawanStatus;
import ua.com.findcoach.exception.StatusUpdateException;
import ua.com.findcoach.i18n.LocalizedMessageResoler;
import ua.com.findcoach.services.CoachService;
import ua.com.findcoach.services.PadawanService;
import ua.com.findcoach.services.UserService;
import ua.com.findcoach.utils.CoachStatusHolder;
import ua.com.findcoach.utils.PadawanStatusHolder;
import ua.com.findcoach.utils.EmailValidator;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AuthenticationController {
    @Autowired
    private EmailValidator emailValidator;

    @Autowired
    private CoachStatusHolder statusHolder;

    @Autowired
    private UserService userService;

    @Autowired
    private CoachService coachService;

    @Autowired
    private PadawanService padawanService;


    @Autowired
    private LocalizedMessageResoler messageResoler;

    private static final int SINGLE_ROW = 1;

    @RequestMapping(method = RequestMethod.POST, value = {"email"})
    @ResponseBody
    public AuthentificationResponse postAnswer(@RequestBody String body, HttpServletRequest request) throws JsonMappingException, JsonParseException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        String decodeJSON = new URLDecoder().decode(body, "UTF-8");
        AuthenticationRequest authenticationRequest = mapper.readValue(decodeJSON, AuthenticationRequest.class);
        if (!emailValidator.validate(authenticationRequest.getEmail())) {
            return new AuthentificationResponse(false, "You wrote wrong message", "");
        }

        String email = authenticationRequest.getEmail();
        Boolean isAuthenticated = userService.authenticateUser(email, request);
        String redirectLink;
        if (isAuthenticated) {
            redirectLink = userService.calculateHomeLinkForUser(authenticationRequest.getEmail());
        } else {
            redirectLink = "";
        }

        return new AuthentificationResponse(isAuthenticated, "", redirectLink);
    }

    @RequestMapping("/coach.html")
    public ModelAndView coachHomePage() throws IOException {
        Map<String, Object> params = new HashMap<>();
        Map<Enum, String> statusMap = new HashMap<>();
        Map<Enum, String> statuses = CoachStatusHolder.getStatusMap();

        statuses
                .entrySet()
                .stream()
                .forEach(enumStringEntry ->
                                statusMap.put(enumStringEntry.getKey(), messageResoler.getMessage(enumStringEntry.getValue()))
                );


        params.put("message", messageResoler.getMessage("titlepage.welcome.coach"));
        params.put("status", statusMap);

        return new ModelAndView("coach", params);
    }

    @RequestMapping("/padawan.html")
    public ModelAndView padawanHomePage() throws IOException {
        Map<String, Object> params = new HashMap<>();
        Map<Enum, String> statusMap = new HashMap<>();
        Map<Enum, String> statuses = PadawanStatusHolder.getStatusMap();

        statuses
                .entrySet()
                .stream()
                .forEach(enumStringEntry ->
                                statusMap.put(enumStringEntry.getKey(), messageResoler.getMessage(enumStringEntry.getValue()))
                );

        params.put("message", messageResoler.getMessage("titlepage.welcome.padawan"));
        params.put("status", statusMap);

        return new ModelAndView("padawan", params);
    }

    @RequestMapping("/index.html")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/coach/status")
    @ResponseBody
    public HttpStatus updateCoachStatus(@RequestParam("status") String status) throws IOException, StatusUpdateException {
        int updatedRowCount = coachService.updateStatus(CoachStatus.valueOf(status));
        if (updatedRowCount == SINGLE_ROW) {
            return HttpStatus.OK;
        }
        throw new StatusUpdateException("Something was going wrong");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/padawan/status")
    @ResponseBody
    public HttpStatus updatePadawanStatus(@RequestParam("status") String status) throws IOException, StatusUpdateException {
        int updatedRowCount = padawanService.updateStatus(PadawanStatus.valueOf(status));
        if (updatedRowCount == SINGLE_ROW) {
            return HttpStatus.OK;
        }
        throw new StatusUpdateException("Something was going wrong");
    }
}

