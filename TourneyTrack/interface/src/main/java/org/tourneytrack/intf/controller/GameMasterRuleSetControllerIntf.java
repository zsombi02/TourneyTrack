package org.tourneytrack.intf.controller;

import org.tourneytrack.intf.dto.data.RuleSetDto;
import org.tourneytrack.intf.dto.request.CreateRuleRequest;
import org.tourneytrack.intf.dto.request.CreateRuleSetRequest;
import org.tourneytrack.intf.dto.request.UpdateRuleRequest;

import java.util.List;

public interface GameMasterRuleSetControllerIntf {

    RuleSetDto createRuleSet(CreateRuleSetRequest request);

    RuleSetDto updateRuleSet(Long id, CreateRuleSetRequest request);

    void deleteRuleSet(Long id);

    List<RuleSetDto> listAll();

    RuleSetDto addRuleToRuleSet(Long id, CreateRuleRequest request);

    RuleSetDto updateRuleInRuleSet(Long id, Long ruleId, UpdateRuleRequest request);

    RuleSetDto deleteRuleFromRuleSet(Long id, Long ruleId);
}
