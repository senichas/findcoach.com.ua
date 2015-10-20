package ua.com.findcoach.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.com.findcoach.i18n.LocalizedMessageResolver;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/padawan/profile")
public class PadawanProfileController {

    @Autowired
    private LocalizedMessageResolver messageResolver;

    @RequestMapping("/home.html")
    public ModelAndView padawanValidator() throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("message", messageResolver.getMessage("titlepage.welcome.padawan"));


        return new ModelAndView("padawanHome", params);
    }
}
