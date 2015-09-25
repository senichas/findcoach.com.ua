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

import java.io.IOException;
import java.net.URLDecoder;

@Controller
public class AuthenticationController {
    @Autowired
    private EmailValidator emailValidator;

    @RequestMapping(method = RequestMethod.POST, value = "email")
    @ResponseBody
    public AuthentificationResponse postAnswer(@RequestBody String body) throws JsonMappingException, JsonParseException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        AuthentificationResponse authentificationResponse = new AuthentificationResponse();
        String decodeJSON = new URLDecoder().decode(body, "UTF-8");
        AuthenticationRequest authenticationRequest = mapper.readValue(decodeJSON, AuthenticationRequest.class);
        if (!emailValidator.validate(authenticationRequest.getEmail())) {
            authentificationResponse.setMessage("You wrote wrong email");
            authentificationResponse.setResult(false);
            return authentificationResponse;
        }
        authentificationResponse.setResult(true);
        return authentificationResponse;
    }
}

