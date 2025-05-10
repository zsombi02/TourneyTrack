package org.tourneytrack.impl.business.service;

import org.tourneytrack.intf.dto.data.RuleSetDto;
import org.tourneytrack.intf.dto.request.CreateRuleSetRequest;
import org.tourneytrack.intf.dto.request.CreateRuleRequest;
import org.tourneytrack.intf.dto.request.UpdateRuleRequest;

import java.util.List;

public interface RuleSetService {
    RuleSetDto createRuleSet(CreateRuleSetRequest request);
    RuleSetDto getRuleSetById(Long id);
    List<RuleSetDto> listAll();
    RuleSetDto addRule(Long ruleSetId, CreateRuleRequest request);
    RuleSetDto updateRule(Long ruleSetId, Long ruleId, UpdateRuleRequest request);
    RuleSetDto deleteRule(Long ruleSetId, Long ruleId);
}
