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
import ua.com.findcoach.api.AuthenticationResponse;
import ua.com.findcoach.api.FacebookAuthRequestDto;
import ua.com.findcoach.services.CoachService;
import ua.com.findcoach.utils.EmailValidator;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLDecoder;

@Controller
@RequestMapping("/authentication")
public class AuthenticationController {
    @Autowired
    private EmailValidator emailValidator;

    @Autowired
    private CoachService coachService;


    @RequestMapping(method = RequestMethod.POST, value = {"email"})
    @ResponseBody
    public AuthenticationResponse postAnswerForSimpleLogin(@RequestBody String body, HttpServletRequest request) throws JsonMappingException, JsonParseException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        String decodeJSON = new URLDecoder().decode(body, "UTF-8");
        AuthenticationRequest authenticationRequest = mapper.readValue(decodeJSON, AuthenticationRequest.class);
        if (!emailValidator.validate(authenticationRequest.getEmail())) {
            return new AuthenticationResponse(false, "You wrote wrong message", "");
        }

        String email = authenticationRequest.getEmail();
        Boolean isAuthenticated = coachService.authenticateUser(email, request);
        String redirectLink;
        if (isAuthenticated) {
            redirectLink = coachService.calculateHomeLink(authenticationRequest.getEmail());
        } else {
            redirectLink = "";
        }

        return new AuthenticationResponse(isAuthenticated, "", redirectLink);
    }

    @RequestMapping(method = RequestMethod.POST, value = {"facebook"})
    @ResponseBody
    public AuthenticationResponse postAnswerForFacebookLogin(@RequestBody FacebookAuthRequestDto facebookDto, HttpServletRequest request) {
        if (!emailValidator.validate(facebookDto.getEmail())) {
            return new AuthenticationResponse(false, "You wrote wrong message", "");
        }

        String email = facebookDto.getEmail();
        Boolean isAuthenticated = coachService.authenticateUser(email, request);
        String redirectLink;
        if (isAuthenticated) {
            redirectLink = coachService.calculateHomeLink(facebookDto.getEmail());
        } else {
            redirectLink = "";
        }
        return new AuthenticationResponse(true, "", redirectLink);
    }

    @RequestMapping("/index.html")
    public ModelAndView index() {
        return new ModelAndView("index");
    }
}

