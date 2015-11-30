package ua.com.findcoach.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.com.findcoach.api.PadawanDTO;
import ua.com.findcoach.domain.Coach;
import ua.com.findcoach.services.CoachService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DENIS on 28.11.2015.
 */
@RestController
@RequestMapping("/coach")
public class CoachProgramController {

    @Autowired
    private CoachService coachService;

    @RequestMapping(method = RequestMethod.GET, value = "/{coachAlias}/padawan")
    public List<PadawanDTO> recieveCoachProgramPadawans(@PathVariable String coachAlias) {
        Coach currentCoach = coachService.retrieveCurrentCoach();
        List<PadawanDTO> padawans = new ArrayList<>();
        currentCoach
                .getProgramList()
                .stream()
                .forEach(program -> padawans.add(new PadawanDTO(
                        program.getPadawan().getPadawanId()
                        , program.getPadawan().getFirstName()
                        , program.getPadawan().getLastName()
                        , program.getPadawan().getEmail()
                        , program.getPadawan().getGender()
                        , program.getProgramId()
                )));
        return padawans;
    }
}
