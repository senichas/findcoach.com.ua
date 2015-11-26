package ua.com.findcoach.repository;

import org.springframework.data.repository.CrudRepository;
import ua.com.findcoach.domain.Program;

public interface ProgramRepository extends CrudRepository<Program, Integer> {
}
