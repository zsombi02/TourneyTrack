package org.tourneytrack.impl.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.tourneytrack.impl.business.service.RuleSetService;
import org.tourneytrack.intf.controller.RuleSetController;
import org.tourneytrack.intf.dto.data.RuleSetDto;
import org.tourneytrack.intf.dto.request.CreateRuleRequest;
import org.tourneytrack.intf.dto.request.CreateRuleSetRequest;
import org.tourneytrack.intf.dto.request.UpdateRuleRequest;

import java.util.List;

@RestController
@RequestMapping("/api/rulesets")
@RequiredArgsConstructor
public class RuleSetControllerImpl implements RuleSetController {

    private final RuleSetService ruleSetService;

    @PostMapping
    public RuleSetDto createRuleSet(@RequestBody CreateRuleSetRequest request) {
        return ruleSetService.createRuleSet(request);
    }

    @GetMapping("/{id}")
    public RuleSetDto getRuleSetById(@PathVariable Long id) {
        return ruleSetService.getRuleSetById(id);
    }

    @GetMapping
    public List<RuleSetDto> listAllRuleSets() {
        return ruleSetService.listAll();
    }

    @PostMapping("/{id}/rules")
    public RuleSetDto addRuleToRuleSet(@PathVariable Long id, @RequestBody CreateRuleRequest request) {
        return ruleSetService.addRule(id, request);
    }

    @PutMapping("/{id}/rules/{ruleId}")
    public RuleSetDto updateRuleInRuleSet(@PathVariable Long id, @PathVariable Long ruleId, @RequestBody UpdateRuleRequest request) {
        return ruleSetService.updateRule(id, ruleId, request);
    }

    @DeleteMapping("/{id}/rules/{ruleId}")
    public RuleSetDto deleteRuleFromRuleSet(@PathVariable Long id, @PathVariable Long ruleId) {
        return ruleSetService.deleteRule(id, ruleId);
    }
}
