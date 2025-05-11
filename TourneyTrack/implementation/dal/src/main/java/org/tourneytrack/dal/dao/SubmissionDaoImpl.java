package org.tourneytrack.dal.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.tourneytrack.dal.entity.SubmissionEntity;
import org.tourneytrack.dal.mapper.SubmissionEntityMapper;
import org.tourneytrack.dal.repository.SubmissionRepository;
import org.tourneytrack.impl.dao.SubmissionDao;
import org.tourneytrack.impl.data.Submission;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SubmissionDaoImpl implements SubmissionDao {

    private final SubmissionRepository repository;
    private final SubmissionEntityMapper mapper;

    @Override
    public Submission save(Submission submission) {
        SubmissionEntity entity = mapper.toEntity(submission);
        return mapper.toModel(repository.save(entity));
    }

    @Override
    public Optional<Submission> findById(Long id) {
        return repository.findById(id).map(mapper::toModel);
    }

    @Override
    public List<Submission> findByCompetitionId(Long competitionId) {
        return repository.findByCompetitionId(competitionId).stream()
                .map(mapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Submission> findByUserId(Long userId) {
        return repository.findByUserId(userId).stream()
                .map(mapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Boolean existsByRuleId(Long ruleId) {
        return repository.existsByRuleId(ruleId);
    }

}
