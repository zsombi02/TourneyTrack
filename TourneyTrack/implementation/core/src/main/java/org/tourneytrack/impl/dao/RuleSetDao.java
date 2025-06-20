package org.tourneytrack.impl.dao;

import org.tourneytrack.impl.data.RuleSet;
import org.tourneytrack.impl.data.Rule;
import java.util.List;
import java.util.Optional;

public interface RuleSetDao {
    RuleSet save(RuleSet ruleSet);
    Optional<RuleSet> findById(Long id);
    List<RuleSet> findAll();
    boolean existsById(Long id);
    void deleteById(Long id);
    RuleSet addRule(Long ruleSetId, Rule rule);
    RuleSet removeRule(Long ruleSetId, Long ruleId);
    RuleSet updateRule(Long ruleSetId, Rule rule);
}
