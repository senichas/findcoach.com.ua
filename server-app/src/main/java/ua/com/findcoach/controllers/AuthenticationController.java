package ua.com.findcoach.controllers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ua.com.findcoach.api.AuthenticationRequest;
import ua.com.findcoach.api.AuthentificationResponse;
import ua.com.findcoach.i18n.LocalizedMessageResoler;
import ua.com.findcoach.services.UserService;
import ua.com.findcoach.utils.EmailValidator;
import ua.com.findcoach.utils.StatusHolder;

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
    private StatusHolder statusHolder;

    @Autowired
    private UserService userService;


    @Autowired
    private LocalizedMessageResoler messageResoler;

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
        Map<Enum, String> statusMap = statusHolder.getStatusMap();
        for (Map.Entry<Enum, String> entry : statusMap.entrySet()) {
            entry.setValue(messageResoler.getMessage(entry.getValue()));
        }
        params.put("message", messageResoler.getMessage("titlepage.welcome.coach"));
        params.put("status", statusMap);

        return new ModelAndView("coach", params);
    }

    @RequestMapping("/padawan.html")
    public ModelAndView padawanValidator() throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("message", messageResoler.getMessage("titlepage.welcome.padawan"));


        return new ModelAndView("padawan", params);
    }

    @RequestMapping("/index.html")
    public ModelAndView index() {
        return new ModelAndView("index");
    }
}

