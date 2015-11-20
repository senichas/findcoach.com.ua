package ua.com.findcoach.controllers;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.findcoach.api.AddPadawanWizardStep1;
import ua.com.findcoach.api.ErrorResponse;

@RestController
@RequestMapping("/coach/{userAlias}/add-padawan-wizard")
public class CoachAddPadawanWizardController {

    @RequestMapping("/step1")
    public ErrorResponse padawanWizardSubmitStep1(@RequestBody AddPadawanWizardStep1 step1Data) {


        ErrorResponse errorResponse = new ErrorResponse("", "");
        return errorResponse;
    }
}
