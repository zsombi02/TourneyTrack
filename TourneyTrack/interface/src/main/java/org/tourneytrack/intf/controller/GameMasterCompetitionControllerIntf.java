package org.tourneytrack.intf.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.tourneytrack.intf.dto.data.CompetitionDto;
import org.tourneytrack.intf.dto.data.ScoreEntryDto;
import org.tourneytrack.intf.dto.data.SubmissionDto;
import org.tourneytrack.intf.dto.request.ApproveSubmissionRequest;
import org.tourneytrack.intf.dto.request.CreateCompetitionRequest;
import org.tourneytrack.intf.dto.request.UpdateCompetitionRequest;

import java.util.List;

@RequestMapping("/api/master/competitions")
public interface GameMasterCompetitionControllerIntf {

    @PostMapping
    void createCompetition(@RequestBody CreateCompetitionRequest request);

    @PutMapping("/{id}")
    void updateCompetition(@PathVariable Long id, @RequestBody UpdateCompetitionRequest request);

    @DeleteMapping("/{id}")
    void deleteCompetition(@PathVariable Long id);

    @PatchMapping("/{id}/stop")
    void stopCompetition(@PathVariable Long id);

    @GetMapping("/{id}/scoreboard")
    List<ScoreEntryDto> getScoreBoard(@PathVariable Long id);

    @GetMapping("/{id}/submissions")
    List<SubmissionDto> getSubmissions(@PathVariable Long id);

    @PostMapping("/{id}/assign-ruleset/{rulesetId}")
    void assignRuleSet(@PathVariable Long id, @PathVariable Long rulesetId);

    @DeleteMapping("/{id}/remove-ruleset/{rulesetId}")
    void removeRuleSet(@PathVariable Long id, @PathVariable Long rulesetId);

    @GetMapping("/my")
    List<CompetitionDto> getMyCompetitions(Authentication authentication);
}