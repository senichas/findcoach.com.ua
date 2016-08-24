package ua.com.findcoach.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.com.findcoach.api.SettingsDto;
import ua.com.findcoach.services.SettingsService;

@Controller
@RequestMapping("/settings")
public class SettingsController {

    @Autowired
    private SettingsService settingsService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public SettingsDto loadSettings() {
        SettingsDto settingsDto = new SettingsDto();
        settingsDto.setDevelopmentMode(settingsService.getDeveloperMode());

        return settingsDto;
    }
}

