package org.tourneytrack.dal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.tourneytrack.dal.entity.CompetitionEntity;

import java.util.List;

public interface CompetitionRepository extends JpaRepository<CompetitionEntity, Long> {

    @Query("SELECT c FROM CompetitionEntity c " +
            "LEFT JOIN c.participants p " +
            "WHERE p.id = :userId OR c.gameMaster.id = :userId")
    List<CompetitionEntity> findAllByUserId(@Param("userId") Long userId);

}