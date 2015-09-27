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
import ua.com.findcoach.api.AuthenticationRequest;
import ua.com.findcoach.api.AuthentificationResponse;
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

    @RequestMapping(method = RequestMethod.POST, value = "email")
    @ResponseBody
    public AuthentificationResponse postAnswer(@RequestBody String body) throws JsonMappingException, JsonParseException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        String decodeJSON = new URLDecoder().decode(body, "UTF-8");
        User user = repository.findByEmail(decodeJSON);
        AuthenticationRequest authenticationRequest = mapper.readValue(decodeJSON, AuthenticationRequest.class);
        AuthentificationResponse authentificationResponse = emailValidator.validate(authenticationRequest.getEmail())
                ? new AuthentificationResponse(true, "")
                : new AuthentificationResponse(false, "You wrote wrong massage");
        return authentificationResponse;
    }
}

