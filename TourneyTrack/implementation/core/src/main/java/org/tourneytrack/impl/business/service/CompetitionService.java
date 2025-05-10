package org.tourneytrack.impl.business.service;

import org.springframework.stereotype.Component;
import org.tourneytrack.intf.dto.data.CompetitionDto;
import org.tourneytrack.intf.dto.data.ScoreBoardDto;
import org.tourneytrack.intf.dto.request.CreateCompetitionRequest;
import org.tourneytrack.intf.dto.request.UpdateCompetitionRequest;

import java.util.List;

public interface CompetitionService {
    void createCompetition(CreateCompetitionRequest request);
    CompetitionDto getById(Long id);
    List<CompetitionDto> listForUser(Long userId);
    ScoreBoardDto getScoreBoard(Long competitionId);
    void assignRuleSet(Long competitionId, Long ruleSetId);
    void removeRuleSet(Long competitionId, Long ruleSetId);;
    void stopCompetition(Long competitionId);
    void update(Long id, UpdateCompetitionRequest request);
    void delete(Long id);


}
