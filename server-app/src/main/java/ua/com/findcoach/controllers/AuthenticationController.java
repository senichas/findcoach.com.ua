package ua.com.findcoach.controllers;

import javax.servlet.http.HttpServletRequest;

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
import ua.com.findcoach.services.AuthenticationService;
import ua.com.findcoach.services.SettingsService;

@Controller
@RequestMapping("/authentication")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private SettingsService settingsService;

    @RequestMapping(method = RequestMethod.POST, value = {"facebook"})
    @ResponseBody
    public AuthenticationResponse postAnswerForFacebookLogin(@RequestBody FacebookAuthRequestDto facebookDto, HttpServletRequest request) {
        Boolean isAuthenticated = authenticationService.authenticateUser(facebookDto, request);

        String redirectLink;
        if (isAuthenticated) {
            redirectLink = authenticationService.getHomeLink();
        } else {
            redirectLink = "";
        }
        return new AuthenticationResponse(isAuthenticated, "", redirectLink);
    }

    @RequestMapping("/index.html")
    public ModelAndView index() {
        if (authenticationService.isCurrentUserTokenAlive()) {
            return new ModelAndView("redirect:" + authenticationService.getHomeLink());
        }
        return new ModelAndView("index");
    }

    @RequestMapping(method = RequestMethod.POST, value = "email")
    @ResponseBody
    public AuthenticationResponse authenticationByEmailInDeveloperMode(@RequestBody AuthenticationRequest authenticationRequest) {
        String redirectLink = "";
//        if (isAuthenticated) {
//            redirectLink = authenticationService.getHomeLink();
//        } else {
//            redirectLink = "";
//        }
        return new AuthenticationResponse(true, "", redirectLink);
    }
}

