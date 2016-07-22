package ua.com.findcoach.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ua.com.findcoach.domain.Program;

import java.util.List;

public interface ProgramRepository extends CrudRepository<Program, Integer> {

    List<Program> findAll();

    Program findOneByProgramId(Integer programId);

    Program findOneByProgramIdAndCoachAlias(Integer programId, String coachAlias);

    @Query("select p from Program p " +
            "where p.coach.alias = :coachAlias " +
            "and p.padawan.padawanId = :padawanId " +
            "and p.programId = :programId")
    Program findOneByProgramIdAndPadawanIdAndCoachAlias(@Param("programId") Integer programId, @Param("padawanId") Integer padawanId,
                                            @Param("coachAlias") String coachAlias);
}
