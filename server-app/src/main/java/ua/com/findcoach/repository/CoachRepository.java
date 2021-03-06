package ua.com.findcoach.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ua.com.findcoach.domain.Coach;

import java.util.List;

public interface CoachRepository extends CrudRepository<Coach, Integer> {

    Coach findByEmail(String email);

    Coach findByCoachId(Integer coachId);

    List<Coach> findAll();

    @Modifying(clearAutomatically = true)
    @Transactional(readOnly = false)
    @Query("UPDATE Coach c SET c.alias = :alias, c.title = :title, c.description = :description WHERE c.email = :email")
    int updateCoachProfileAttributes(@Param("alias") String alias,
                                     @Param("title") String title,
                                     @Param("description") String description,
                                     @Param("email") String email);

    //   @Query("SELECT c.alias, c.title, c.description FROM Coach c WHERE c.email = :email")
    @Query("SELECT c FROM Coach c WHERE c.email = :email")
    Coach receiveCoachProfileAttributes(@Param("email") String email);
}
