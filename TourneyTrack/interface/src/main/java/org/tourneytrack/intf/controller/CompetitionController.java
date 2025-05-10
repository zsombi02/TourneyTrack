package org.tourneytrack.intf.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.tourneytrack.intf.dto.data.CompetitionDto;
import org.tourneytrack.intf.dto.data.ScoreBoardDto;
import org.tourneytrack.intf.dto.request.CreateCompetitionRequest;
import org.tourneytrack.intf.dto.request.UpdateCompetitionRequest;

import java.util.List;

public interface CompetitionController {
    void createCompetition(CreateCompetitionRequest request);
    CompetitionDto getById(@PathVariable Long id);
    List<CompetitionDto> listMyCompetitions(@RequestParam Long userId);
    ScoreBoardDto getScoreBoard(@PathVariable Long competitionId);
    void assignRuleSet(@PathVariable Long competitionId, @PathVariable Long ruleSetId);
    void removeRuleSet(@PathVariable Long competitionId, @PathVariable Long ruleSetId);
    void stopCompetition(@PathVariable Long competitionId);
    void updateCompetition(@PathVariable Long id, UpdateCompetitionRequest request); // újrahasználjuk a request DTO-t
    void deleteCompetition(@PathVariable Long id);

}
