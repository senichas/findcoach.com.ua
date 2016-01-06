package ua.com.findcoach.repository;

import org.springframework.data.repository.CrudRepository;
import ua.com.findcoach.domain.Event;

public interface EventRepository extends CrudRepository<Event, Long> {
    /*@Query("select e from Event e " +
            "join e.coaches ec " +
            "join ec.coach c " +
            "where c.alias = :alias")
    List<Event> findAllByAlias(@Param("alias") String alias);*/
}
