package org.tourneytrack.impl.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.tourneytrack.intf.controller.GameMasterCompetitionControllerIntf;
import org.tourneytrack.intf.dto.data.CompetitionDto;
import org.tourneytrack.intf.dto.data.ScoreEntryDto;
import org.tourneytrack.intf.dto.data.SubmissionDto;
import org.tourneytrack.intf.dto.request.ApproveSubmissionRequest;
import org.tourneytrack.intf.dto.request.CreateCompetitionRequest;
import org.tourneytrack.intf.dto.request.UpdateCompetitionRequest;

import java.util.List;

@RestController
@RequestMapping("/api/master/competitions")
@PreAuthorize("hasRole('GAME_MASTER')")
public class GameMasterCompetitionController extends AbstractController implements GameMasterCompetitionControllerIntf {

    @PostMapping
    public void createCompetition(@RequestBody CreateCompetitionRequest request) {
        competitionService.createCompetition(request);
    }

    @PutMapping("/{id}")
    public void updateCompetition(@PathVariable Long id, @RequestBody UpdateCompetitionRequest request) {
        competitionService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteCompetition(@PathVariable Long id) {
        competitionService.delete(id);
    }

    @PatchMapping("/{id}/stop")
    public void stopCompetition(@PathVariable Long id) {
        competitionService.stopCompetition(id);
    }

    @GetMapping("/{id}/scoreboard")
    public List<ScoreEntryDto> getScoreBoard(@PathVariable Long id) {
        return competitionService.getScoreBoard(id);
    }

    @GetMapping("/{id}/submissions")
    public List<SubmissionDto> getSubmissions(@PathVariable Long id) {
        return submissionService.getByCompetition(id);
    }

    @PostMapping("/{id}/assign-ruleset/{rulesetId}")
    public void assignRuleSet(@PathVariable Long id, @PathVariable Long rulesetId) {
        competitionService.assignRuleSet(id, rulesetId);
    }

    @DeleteMapping("/{id}/remove-ruleset/{rulesetId}")
    public void removeRuleSet(@PathVariable Long id, @PathVariable Long rulesetId) {
        competitionService.removeRuleSet(id, rulesetId);
    }

    @GetMapping("/my")
    public List<CompetitionDto> getMyCompetitions(Authentication authentication) {
        String email = authentication.getName();
        Long userId = userService.getByEmail(email).getId();
        return competitionService.listByGameMaster(userId);
    }
}
