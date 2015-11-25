package ua.com.findcoach.repository;

import org.springframework.data.repository.CrudRepository;
import ua.com.findcoach.domain.Measure;

public interface MeasureRepository extends CrudRepository<Measure, Integer> {

}
