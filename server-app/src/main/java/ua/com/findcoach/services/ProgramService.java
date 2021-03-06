package ua.com.findcoach.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.findcoach.domain.Cycle;
import ua.com.findcoach.domain.Program;
import ua.com.findcoach.repository.ProgramRepository;

@Service
@Transactional
public class ProgramService {

    @Autowired
    private ProgramRepository programRepository;

    public Program saveProgram(Program program) {
        return programRepository.save(program);
    }

    public Program findProgramById(Integer programId) {
        return programRepository.findOneByProgramId(programId);
    }

    public Program findOneByProgramIdAndCoachAlias(Integer programId, String coachAlias) {
        return programRepository.findOneByProgramIdAndCoachAlias(programId, coachAlias);
    }

    public Program addNewCycleToProgram(Integer programId, String cycleName, String cycleDescription) {
        Program program = findProgramById(programId);
        Cycle cycle = new Cycle();
        cycle.setName(cycleName);
        cycle.setNotes(cycleDescription);

        program.getCycles().add(cycle);

        return saveProgram(program);
    }

    public Program findProgramByProgramIdAndPadawanIdAndProgramId(String coachAlias, Integer padawanId, Integer programId) {
        return programRepository.findOneByProgramIdAndPadawanIdAndCoachAlias(programId, padawanId, coachAlias);
    }
}
