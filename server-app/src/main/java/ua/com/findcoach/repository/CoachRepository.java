package ua.com.findcoach.repository;

import org.springframework.data.repository.Repository;
import ua.com.findcoach.domain.Coach;

import java.util.List;

/**
 * Created by DENIS on 10.10.2015.
 */
public interface CoachRepository extends Repository<Coach, Integer> {
    List<Coach> findAll();
}
