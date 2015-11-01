package ua.com.findcoach.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import ua.com.findcoach.domain.Event;

import java.util.List;

public interface EventRepository extends Repository<Event, Long> {
    @Query("select e from Event e " +
            "join e.participants ep " +
            "join ep.user u " +
            "where u.alias = :alias")
    List<Event> findAllByAlias(@Param("alias") String alias);
}
