package ua.com.findcoach.controllers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ua.com.findcoach.api.AuthenticationRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.net.URLDecoder;

@Controller
public class AuthenticationController {
    @RequestMapping(method = RequestMethod.POST, value = "email")
    @ResponseBody
    public String postAnswer(@RequestBody String body) throws JsonMappingException, JsonParseException, IOException {
        EmailValidator emailValidator = new EmailValidator();
        ObjectMapper mapper = new ObjectMapper();
        String decodeJSON = new URLDecoder().decode(body, "UTF-8");
        System.out.println(decodeJSON);
        AuthenticationRequest pojo = mapper.readValue(decodeJSON, AuthenticationRequest.class);
        System.out.println(pojo.getEmail());
        if (!emailValidator.validate(pojo.getEmail())) {
            return "You wrote wrong email";
        }
        return "ok";
    }
}

