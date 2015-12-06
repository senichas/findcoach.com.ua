package ua.com.findcoach.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
}
