package ua.com.findcoach.repository;

import org.springframework.data.repository.CrudRepository;
import ua.com.findcoach.domain.Padawan;

import java.util.List;

/**
 * Created by Daemon on 13.10.2015.
 */
public interface PadawanRepository extends CrudRepository<Padawan, Integer> {
    List<Padawan> findAll();

    Padawan saveAndFlush(Padawan padawan);
}
