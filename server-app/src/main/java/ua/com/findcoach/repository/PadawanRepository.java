package ua.com.findcoach.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ua.com.findcoach.domain.Padawan;
import ua.com.findcoach.domain.PadawanStatus;

import java.util.List;

/**
 * Created by Aleksander Krasilnikov on 13.10.2015.
 */
public interface PadawanRepository extends CrudRepository<Padawan, Integer> {
    List<Padawan> findAll();

    @Modifying(clearAutomatically = true)
    @Transactional(readOnly = false)
    @Query("UPDATE Padawan p SET p.status = :coachStatus WHERE p.email = :email")
    int updatePadawanStatus(@Param("padawanStatus") PadawanStatus padawanStatus, @Param("email") String email);

}
