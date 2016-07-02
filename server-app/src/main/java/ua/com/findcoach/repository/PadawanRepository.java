package ua.com.findcoach.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ua.com.findcoach.domain.Padawan;

import java.util.List;

/**
 * Created by Daemon on 13.10.2015.
 */
public interface PadawanRepository extends CrudRepository<Padawan, Integer> {
    List<Padawan> findAll();

    Padawan findByPadawanId(Integer padawanId);

    Padawan saveAndFlush(Padawan padawan);

    @Query("SELECT p FROM Padawan p WHERE p.createdBy.alias = :coachAlias")
    List<Padawan> findActivePadawansByCoachAlias(@Param("coachAlias") String coachAlias);

}
