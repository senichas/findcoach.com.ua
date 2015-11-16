package ua.com.findcoach.repository;

import org.springframework.data.repository.CrudRepository;
import ua.com.findcoach.domain.Program;

import java.util.List;

/**
 * Created by DENIS on 16.11.2015.
 */
public interface ProgramRepository extends CrudRepository<Program, Integer> {

    List<Program> findAll();
}
