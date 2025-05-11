package org.tourneytrack.impl.dao;

import org.tourneytrack.impl.data.Submission;

import java.util.List;
import java.util.Optional;

public interface SubmissionDao {
    Submission save(Submission submission);
    Optional<Submission> findById(Long id);
    List<Submission> findByCompetitionId(Long competitionId);
    List<Submission> findByUserId(Long userId);
    Boolean existsByRuleId(Long ruleId);

}
