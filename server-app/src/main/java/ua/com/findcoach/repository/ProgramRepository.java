package ua.com.findcoach.repository;

import org.springframework.data.repository.CrudRepository;
import ua.com.findcoach.domain.Program;

import java.util.List;

public interface ProgramRepository extends CrudRepository<Program, Integer> {

    List<Program> findAll();

    Program findOneByProgramId(Integer programId);
}
