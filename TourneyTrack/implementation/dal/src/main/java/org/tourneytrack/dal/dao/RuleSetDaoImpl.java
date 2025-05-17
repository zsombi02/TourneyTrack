package org.tourneytrack.dal.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.tourneytrack.dal.entity.RuleEntity;
import org.tourneytrack.dal.entity.RuleSetEntity;
import org.tourneytrack.dal.mapper.RuleEntityMapper;
import org.tourneytrack.dal.mapper.RuleSetEntityMapper;
import org.tourneytrack.dal.repository.RuleSetRepository;
import org.tourneytrack.impl.dao.RuleSetDao;
import org.tourneytrack.impl.data.Rule;
import org.tourneytrack.impl.data.RuleSet;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor

public class RuleSetDaoImpl implements RuleSetDao {
    private final RuleSetRepository repository;
    private final RuleSetEntityMapper mapper;
    private final RuleEntityMapper ruleEntityMapper;

    @Override
    public RuleSet save(RuleSet ruleSet) {
        RuleSetEntity entity = mapper.toEntity(ruleSet);
        return mapper.toModel(repository.save(entity));
    }

    @Override
    public Optional<RuleSet> findById(Long id) {
        return repository.findById(id)
                .map(mapper::toModel);
    }

    @Override
    public List<RuleSet> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    // --- ÚJ: Szabály hozzáadása ---
    public RuleSet addRule(Long ruleSetId, Rule rule) {
        RuleSetEntity ruleSet = repository.findById(ruleSetId).orElseThrow();
        RuleEntity entity = ruleEntityMapper.toEntity(rule);
        ruleSet.getRules().add(entity);
        repository.save(ruleSet);
        return mapper.toModel(ruleSet);
    }

    // --- ÚJ: Szabály törlése ---
    public RuleSet removeRule(Long ruleSetId, Long ruleId) {
        RuleSetEntity ruleSet = repository.findById(ruleSetId).orElseThrow();
        ruleSet.getRules().removeIf(r -> r.getId().equals(ruleId));
        repository.save(ruleSet);
        return mapper.toModel(ruleSet);
    }

    // --- ÚJ: Szabály módosítása ---
    public RuleSet updateRule(Long ruleSetId, Rule rule) {
        RuleSetEntity ruleSet = repository.findById(ruleSetId).orElseThrow();
        for (RuleEntity entity : ruleSet.getRules()) {
            if (entity.getId().equals(rule.getId())) {
                entity.setName(rule.getName());
                entity.setDescription(rule.getDescription());
                entity.setPoints(rule.getPoints());
                entity.setRepetitions(rule.getRepetitions());
                break;
            }
        }
        repository.save(ruleSet);
        return mapper.toModel(ruleSet);
    }
}

