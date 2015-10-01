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
import ua.com.findcoach.services.UserService;
import ua.com.findcoach.utils.EmailValidator;

import java.io.IOException;
import java.net.URLDecoder;

@Controller
public class AuthenticationController {
    @Autowired
    private EmailValidator emailValidator;

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST, value = {"email"})
    @ResponseBody
    public AuthentificationResponse postAnswer(@RequestBody String body) throws JsonMappingException, JsonParseException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        String decodeJSON = new URLDecoder().decode(body, "UTF-8");
        AuthenticationRequest authenticationRequest = mapper.readValue(decodeJSON, AuthenticationRequest.class);
        if (!emailValidator.validate(authenticationRequest.getEmail())) {
            return new AuthentificationResponse(false, "You wrote wrong message", "");
        }

        String redirectLink = userService.calculateHomeLinkForUser(authenticationRequest.getEmail());

        return new AuthentificationResponse(true, "", redirectLink);
    }

    @RequestMapping("/coach")
    public ModelAndView coachValidator() throws IOException {
        return new ModelAndView("coach");
    }

    @RequestMapping("/padawan.html")
    public ModelAndView padawanValidator() throws IOException {

        return new ModelAndView("padawan");
    }

    @RequestMapping("/index.html")
    public ModelAndView index() {
        return new ModelAndView("index");
    }
}

