package org.tourneytrack.intf.controller;

import org.springframework.web.bind.annotation.*;
import org.tourneytrack.intf.dto.data.RuleSetDto;
import org.tourneytrack.intf.dto.request.CreateRuleRequest;
import org.tourneytrack.intf.dto.request.CreateRuleSetRequest;
import org.tourneytrack.intf.dto.request.UpdateRuleRequest;

import java.util.List;

@RequestMapping("/api/master/rulesets")
public interface GameMasterRuleSetControllerIntf {

    @PostMapping
    RuleSetDto createRuleSet(@RequestBody CreateRuleSetRequest request);

    @PutMapping("/{id}")
    RuleSetDto updateRuleSet(@PathVariable Long id, @RequestBody CreateRuleSetRequest request);

    @DeleteMapping("/{id}")
    void deleteRuleSet(@PathVariable Long id);

    @GetMapping
    List<RuleSetDto> listAll();

    @PostMapping("/{id}/rules")
    RuleSetDto addRuleToRuleSet(@PathVariable Long id, @RequestBody CreateRuleRequest request);

    @PutMapping("/{id}/rules/{ruleId}")
    RuleSetDto updateRuleInRuleSet(@PathVariable Long id, @PathVariable Long ruleId, @RequestBody UpdateRuleRequest request);

    @DeleteMapping("/{id}/rules/{ruleId}")
    RuleSetDto deleteRuleFromRuleSet(@PathVariable Long id, @PathVariable Long ruleId);
}
