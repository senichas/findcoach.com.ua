package ua.com.findcoach.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ua.com.findcoach.api.PadawanDTO;
import ua.com.findcoach.domain.Coach;
import ua.com.findcoach.domain.Program;
import ua.com.findcoach.services.CoachService;
import ua.com.findcoach.services.ProgramService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/coach")
public class CoachProgramController {

    @Autowired
    private CoachService coachService;

    @Autowired
    private ProgramService programService;

    @ResponseBody
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

    @RequestMapping(method = RequestMethod.GET, value = "/{coachAlias}/program/{programId}.html")
    public ModelAndView programDetailPage(@PathVariable String coachAlias, @PathVariable Integer programId) {
        Map<String, Object> parameters = new HashMap<>();

        Program program = programService.findProgramById(programId);


        parameters.put("programName", program.getName());
        return new ModelAndView("padawan-management/programDetails", parameters);
    }
}
