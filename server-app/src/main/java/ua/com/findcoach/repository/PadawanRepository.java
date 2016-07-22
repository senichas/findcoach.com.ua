package ua.com.findcoach.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ua.com.findcoach.domain.Padawan;

import java.util.List;

public interface PadawanRepository extends CrudRepository<Padawan, Integer> {
    List<Padawan> findAll();

    @Query("SELECT p FROM Padawan p WHERE p.createdBy.alias = :coachAlias AND p.padawanId = :padawanId AND p.active = true")
    Padawan findByPadawanIdAndCoachAlias(@Param("padawanId") Integer padawanId, @Param("coachAlias") String coachAlias);

    Padawan saveAndFlush(Padawan padawan);

    @Query("SELECT p FROM Padawan p WHERE p.createdBy.alias = :coachAlias AND p.active = true ORDER BY p.padawanId")
    List<Padawan> findActivePadawansByCoachAlias(@Param("coachAlias") String coachAlias);

}
