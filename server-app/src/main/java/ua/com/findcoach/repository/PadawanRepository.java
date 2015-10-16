package ua.com.findcoach.repository;

import org.springframework.data.repository.Repository;
import ua.com.findcoach.domain.Padawan;

import java.util.List;

/**
 * Created by Daemon on 13.10.2015.
 */
public interface PadawanRepository extends Repository<Padawan, Integer> {
    List<Padawan> findAll();
}
