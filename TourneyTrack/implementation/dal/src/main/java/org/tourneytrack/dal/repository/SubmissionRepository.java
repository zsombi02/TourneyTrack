package org.tourneytrack.dal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tourneytrack.dal.entity.SubmissionEntity;

import java.util.List;

public interface SubmissionRepository extends JpaRepository<SubmissionEntity, Long> {
    List<SubmissionEntity> findByCompetitionId(Long competitionId);
    List<SubmissionEntity> findByUserId(Long userId);
    boolean existsByRuleId(Long ruleId);

}
