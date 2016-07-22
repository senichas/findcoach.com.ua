package ua.com.findcoach.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ua.com.findcoach.domain.Event;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends CrudRepository<Event, Long> {
    @Query("SELECT DISTINCT e FROM Program p " +
            "JOIN p.coach ch " +
            "JOIN p.cycles c " +
            "JOIN  c.events e " +
            "JOIN e.recurrences r " +
            "WHERE ch.alias = :coachAlias " +
            "AND r.startDate >= :startDate " +
            "AND r.endDate < :endDate")
    List<Event> findEventsInPeriodForCoach(@Param("coachAlias") String coachAlias,
                                           @Param("startDate") LocalDateTime startDate,
                                           @Param("endDate") LocalDateTime endDate);
}
