package ua.com.findcoach.repository;

import org.springframework.data.repository.CrudRepository;
import ua.com.findcoach.domain.Padawan;

import java.util.List;


public interface PadawanRepository extends CrudRepository<Padawan, Integer> {
    List<Padawan> findAll();
    Padawan findByPadawanId(Integer padawanId);
    Padawan saveAndFlush(Padawan padawan);

}
