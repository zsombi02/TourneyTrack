package org.tourneytrack.dal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tourneytrack.dal.entity.ScoreBoardEntity;

public interface ScoreBoardRepository extends JpaRepository<ScoreBoardEntity, Long> {
}
