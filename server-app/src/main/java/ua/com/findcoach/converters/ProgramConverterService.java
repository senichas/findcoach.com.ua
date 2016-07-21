package ua.com.findcoach.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.findcoach.api.ProgramDetailsDto;
import ua.com.findcoach.api.ProgramDto;
import ua.com.findcoach.domain.Goal;
import ua.com.findcoach.domain.Program;
import ua.com.findcoach.i18n.LocalizedMessageResolver;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProgramConverterService {
    @Resource
    private Map<Goal, String> programGoalsLocalizationKeys;

    @Autowired
    private LocalizedMessageResolver messageResolver;

    @Autowired
    private CycleConverterService cycleConverterService;

    public ProgramDetailsDto convertToDetailedDto(Program program) {
        ProgramDetailsDto programDetailsDto = new ProgramDetailsDto();
        programDetailsDto.setProgramId(program.getProgramId());
        programDetailsDto.setGoal(program.getGoal());
        programDetailsDto.setProgramName(program.getName());
        programDetailsDto.setCoachAlias(program.getCoach().getAlias());
        programDetailsDto.setCycles(cycleConverterService.convertCyclesListToDtos(program.getCycles()));
        return programDetailsDto;
    }

    public ProgramDto convertProgramToDto(Program program) {
        ProgramDto dto = new ProgramDto();
        dto.setProgramId(program.getProgramId());
        dto.setGoal(program.getGoal());
        String localizedGoal = messageResolver.getMessage(programGoalsLocalizationKeys.get(program.getGoal()));
        dto.setLocalizedGoal(localizedGoal);
        dto.setProgramName(program.getName());

        return dto;
    }

    public List<ProgramDto> convertProgramsToDtos(List<Program> programs) {
        return programs.stream().map(program -> convertProgramToDto(program)).collect(Collectors.toList());
    }

}
