package org.tourneytrack.dal.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.tourneytrack.dal.entity.RuleEntity;
import org.tourneytrack.dal.entity.RuleSetEntity;
import org.tourneytrack.dal.mapper.RuleEntityMapper;
import org.tourneytrack.dal.mapper.RuleSetEntityMapper;
import org.tourneytrack.dal.repository.RuleRepository;
import org.tourneytrack.dal.repository.RuleSetRepository;
import org.tourneytrack.impl.dao.RuleDao;
import org.tourneytrack.impl.data.Rule;

@Component
@RequiredArgsConstructor
public class RuleDaoImpl implements RuleDao {

    private final RuleRepository repository;
    private final RuleSetRepository ruleSetRepository;
    private final RuleSetEntityMapper ruleSetEntityMapper;
    private final RuleEntityMapper ruleEntityMapper;

    @Override
    public void save(Rule rule, Long ruleSetId) {
        RuleSetEntity ruleSet = ruleSetRepository.findById(ruleSetId).orElseThrow();
        RuleEntity entity = new RuleEntity();
        entity.setName(rule.getName());
        entity.setDescription(rule.getDescription());
        entity.setPoints(rule.getPoints());
        entity.setRepetitions(rule.getRepetitions());
//        entity.setRuleSet(ruleSet);
        repository.save(entity);
    }

    @Override
    public void update(Rule rule) {
        RuleEntity entity = repository.findById(rule.getId()).orElseThrow();
        entity.setName(rule.getName());
        entity.setDescription(rule.getDescription());
        entity.setPoints(rule.getPoints());
        entity.setRepetitions(rule.getRepetitions());
        repository.save(entity);
    }

    @Override
    public void deleteById(Long ruleId) {
        repository.deleteById(ruleId);
    }

    @Override
    public Rule getRuleById(Long ruleId) {
        RuleEntity entity = repository.findById(ruleId).orElseThrow();

        return ruleEntityMapper.toModel(entity);
    }
}
