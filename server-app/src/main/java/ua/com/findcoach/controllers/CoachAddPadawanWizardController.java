package ua.com.findcoach.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.findcoach.api.ErrorResponse;

@RestController
@RequestMapping("/coach/{userAlias}/add-padawan-wizard")
public class CoachAddPadawanWizardController {
    public ErrorResponse addPadawanToSession() {


        ErrorResponse errorResponse = new ErrorResponse("", "");
        return errorResponse;
    }
}
