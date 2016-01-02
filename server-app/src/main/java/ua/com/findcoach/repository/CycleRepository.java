package ua.com.findcoach.repository;

import org.springframework.data.repository.CrudRepository;
import ua.com.findcoach.domain.Cycle;

public interface CycleRepository extends CrudRepository<Cycle, Long> {
}
