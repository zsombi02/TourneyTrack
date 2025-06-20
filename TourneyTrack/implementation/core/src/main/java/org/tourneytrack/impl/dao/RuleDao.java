package org.tourneytrack.impl.dao;

import org.tourneytrack.impl.data.Rule;

public interface RuleDao {
    Rule save(Rule rule, Long ruleSetId);
    void update(Rule rule);
    void deleteById(Long ruleId);
    Rule getRuleById(Long ruleId);
}
