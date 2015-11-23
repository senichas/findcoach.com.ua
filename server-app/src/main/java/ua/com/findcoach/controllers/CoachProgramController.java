package ua.com.findcoach.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.com.findcoach.domain.Coach;
import ua.com.findcoach.domain.Padawan;
import ua.com.findcoach.domain.Program;
import ua.com.findcoach.services.CoachService;
import ua.com.findcoach.services.ProgramService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/coach")
public class CoachProgramController {

    @Autowired
    private CoachService coachService;

    @Autowired
    private ProgramService programService;

    @RequestMapping(method = RequestMethod.GET, value = "/{coachAlias}/padawan")
    public List<Padawan> recieveCoachProgramPadawans(@PathVariable String coachAlias) {
        List<Padawan> padawans = new ArrayList<>();
        Coach coach = coachService.retrieveCurrentCoach();
        for(Program program : coach.getProgramList()){
            padawans.add(program.getPadawan());
        }
        return padawans;
    }
}
