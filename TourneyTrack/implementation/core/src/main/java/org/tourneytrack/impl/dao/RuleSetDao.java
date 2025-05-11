package org.tourneytrack.impl.dao;

import org.tourneytrack.impl.data.RuleSet;

import java.util.List;
import java.util.Optional;

public interface RuleSetDao {
    RuleSet save(RuleSet ruleSet);
    Optional<RuleSet> findById(Long id);
    List<RuleSet> findAll();
    boolean existsById(Long id);
    void deleteById(Long id);
}
