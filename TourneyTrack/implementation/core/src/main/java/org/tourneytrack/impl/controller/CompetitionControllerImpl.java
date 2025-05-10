package org.tourneytrack.impl.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tourneytrack.impl.business.service.CompetitionService;
import org.tourneytrack.intf.controller.CompetitionController;
import org.tourneytrack.intf.dto.data.CompetitionDto;
import org.tourneytrack.intf.dto.data.ScoreBoardDto;
import org.tourneytrack.intf.dto.request.CreateCompetitionRequest;
import org.tourneytrack.intf.dto.request.UpdateCompetitionRequest;

import java.util.List;

@RestController
@RequestMapping("/api/competitions")
@RequiredArgsConstructor
public class CompetitionControllerImpl implements CompetitionController {

    @Autowired
    private final CompetitionService competitionService;

    @PostMapping
    public void createCompetition(@RequestBody CreateCompetitionRequest request) {
        this.competitionService.createCompetition(request);
    }

    @GetMapping("/{id}")
    public CompetitionDto getById(@PathVariable Long id) {
        return this.competitionService.getById(id);
    }

    @GetMapping
    public List<CompetitionDto> listMyCompetitions(@RequestParam Long userId) {
        return this.competitionService.listForUser(userId);
    }

    @GetMapping("/{id}/scoreboard")
    public ScoreBoardDto getScoreBoard(@PathVariable Long id) {
        return this.competitionService.getScoreBoard(id);
    }

    @PostMapping("/{id}/rulesets/{ruleSetId}")
    public void assignRuleSet(@PathVariable("id") Long competitionId,
                              @PathVariable("ruleSetId") Long ruleSetId) {
        competitionService.assignRuleSet(competitionId, ruleSetId);
    }

    @DeleteMapping("/{id}/rulesets/{ruleSetId}")
    public void removeRuleSet(@PathVariable("id") Long competitionId,
                              @PathVariable("ruleSetId") Long ruleSetId) {
        competitionService.removeRuleSet(competitionId, ruleSetId);
    }

    @PatchMapping("/{id}/stop")
    public void stopCompetition(@PathVariable("id") Long competitionId) {
        competitionService.stopCompetition(competitionId);
    }

    @Override
    public void updateCompetition(Long id, UpdateCompetitionRequest request) {
        competitionService.update(id, request);
    }

    @Override
    public void deleteCompetition(Long id) {
        competitionService.delete(id);
    }

}