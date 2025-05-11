package org.tourneytrack.impl.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.tourneytrack.intf.controller.GameMasterRuleSetControllerIntf;
import org.tourneytrack.intf.dto.data.RuleSetDto;
import org.tourneytrack.intf.dto.request.CreateRuleRequest;
import org.tourneytrack.intf.dto.request.CreateRuleSetRequest;
import org.tourneytrack.intf.dto.request.UpdateRuleRequest;

import java.util.List;

@RestController
@RequestMapping("/api/master/rulesets")
@PreAuthorize("hasRole('GAME_MASTER')")
public class GameMasterRuleSetController extends AbstractController implements GameMasterRuleSetControllerIntf {

    @PostMapping
    public RuleSetDto createRuleSet(@RequestBody CreateRuleSetRequest request) {
        return ruleSetService.createRuleSet(request);
    }

    @PutMapping("/{id}")
    public RuleSetDto updateRuleSet(@PathVariable Long id, @RequestBody CreateRuleSetRequest request) {
        return ruleSetService.updateRuleSet(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteRuleSet(@PathVariable Long id) {
        ruleSetService.deleteRuleSet(id);
    }

    @GetMapping
    public List<RuleSetDto> listAll() {
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
