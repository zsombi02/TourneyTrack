package org.tourneytrack.intf.controller;

import org.tourneytrack.intf.dto.data.RuleSetDto;
import org.tourneytrack.intf.dto.request.CreateRuleSetRequest;
import org.tourneytrack.intf.dto.request.CreateRuleRequest;
import org.tourneytrack.intf.dto.request.UpdateRuleRequest;

import java.util.List;

public interface RuleSetController {
    RuleSetDto createRuleSet(CreateRuleSetRequest request);
    RuleSetDto getRuleSetById(Long id);
    List<RuleSetDto> listAllRuleSets();

    RuleSetDto addRuleToRuleSet(Long ruleSetId, CreateRuleRequest request);
    RuleSetDto updateRuleInRuleSet(Long ruleSetId, Long ruleId, UpdateRuleRequest request);
    RuleSetDto deleteRuleFromRuleSet(Long ruleSetId, Long ruleId);
}
