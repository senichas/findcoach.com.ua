package ua.com.findcoach.repository;

import ua.com.findcoach.domain.Cycle;

import org.springframework.data.repository.CrudRepository;

public interface CycleRepository extends CrudRepository<Cycle, Long> {

	Cycle findOneByCycleId(Integer cycleId);
}
