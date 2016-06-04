package ua.com.findcoach.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.findcoach.api.ProgramDetailsDto;
import ua.com.findcoach.domain.Program;

@Service
public class ProgramConverterService {
    @Autowired
    private CycleConverterService cycleConverterService;

    public ProgramDetailsDto convertToDetailedDto(Program program) {
        ProgramDetailsDto programDetailsDto = new ProgramDetailsDto();
        programDetailsDto.setProgramId(program.getProgramId());
        programDetailsDto.setGoal(program.getGoal());
        programDetailsDto.setProgramName(program.getName());
        programDetailsDto.setCoachAlias(program.getCoach().getAlias());
        programDetailsDto.setCycleDtos(cycleConverterService.convertCyclesListToDtos(program.getCycles()));
        return programDetailsDto;
    }
}
