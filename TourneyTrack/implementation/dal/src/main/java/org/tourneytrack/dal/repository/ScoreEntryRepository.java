package org.tourneytrack.dal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tourneytrack.dal.entity.ScoreEntryEntity;

import java.util.List;

public interface ScoreEntryRepository extends JpaRepository<ScoreEntryEntity, Long> {
    List<ScoreEntryEntity> findAllByScoreBoardId(Long scoreBoardId);
}
