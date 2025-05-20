package org.tourneytrack.impl.business.service;

import org.tourneytrack.intf.dto.data.CompetitionDto;
import org.tourneytrack.intf.dto.data.RuleSetDto;
import org.tourneytrack.intf.dto.data.ScoreEntryDto;
import org.tourneytrack.intf.dto.request.CreateCompetitionRequest;
import org.tourneytrack.intf.dto.request.CreateRuleSetRequest;
import org.tourneytrack.intf.dto.request.UpdateCompetitionRequest;

import java.util.List;

public interface CompetitionService {
    void createCompetition(CreateCompetitionRequest request);
    CompetitionDto getById(Long id);
    List<CompetitionDto> listForUser(Long userId);
    List<ScoreEntryDto> getScoreBoard(Long competitionId);
    void assignRuleSet(Long competitionId, Long ruleSetId);
    void removeRuleSet(Long competitionId, Long ruleSetId);;
    void stopCompetition(Long competitionId);
    void update(Long id, UpdateCompetitionRequest request);
    void delete(Long id);
    void joinCompetition(Long competitionId, Long userId);
    void leaveCompetition(Long competitionId, Long userId);
    List<CompetitionDto> listJoinedCompetitions(Long userId);
    boolean isParticipant(Long competitionId, Long userId);
    List<CompetitionDto> listAll();
    List<CompetitionDto> listByGameMaster(Long gameMasterId);


}
