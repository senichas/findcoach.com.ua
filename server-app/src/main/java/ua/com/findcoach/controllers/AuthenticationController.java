package ua.com.findcoach.controllers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.javafx.sg.PGShape;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ua.com.findcoach.api.AuthenticationRequest;
import ua.com.findcoach.domain.User;
import ua.com.findcoach.repository.UserRepository;

import java.io.IOException;
import java.net.URLDecoder;

@Controller
public class AuthenticationController {
    @Autowired
    private EmailValidator emailValidator;
    @Autowired
    private UserRepository repository;
    final static String COACH_REDIRECT = "{\"redirect\":\"/profile/coach.html\"}";
    final static String PADAWAN_REDIRECT = "{\"redirect\":\"/profile/padawan.html\"}";
    public User user;

    @RequestMapping(method = RequestMethod.POST, value = {"email"})
    @ResponseBody
    public String postAnswer(@RequestBody String body) throws JsonMappingException, JsonParseException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        String decodeJSON = new URLDecoder().decode(body, "UTF-8");
        AuthenticationRequest authenticationRequest = mapper.readValue(decodeJSON, AuthenticationRequest.class);
        user = repository.findByEmail(authenticationRequest.getEmail());
        String result = user.getIsCoach() == 0 ? COACH_REDIRECT : PADAWAN_REDIRECT;
//        AuthentificationResponse authentificationResponse = emailValidator.validate(authenticationRequest.getEmail())
//                ? new AuthentificationResponse(true, "")
//                : new AuthentificationResponse(false, "You wrote wrong massage");
        return result;
    }

    @RequestMapping("/coach")
    public ModelAndView coachValidator() throws IOException {
        if (user == null) {
            return new ModelAndView("403");
        }
        return new ModelAndView("coach");
    }

    @RequestMapping("/padawan.html")
    public ModelAndView padawanValidator() throws IOException {
        if (user == null) {
            return new ModelAndView("403");
        }
        return new ModelAndView("padawan");
    }

    @RequestMapping("/index.html")
    public ModelAndView index() {
        return new ModelAndView("index");
    }
}

