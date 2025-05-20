package org.tourneytrack.impl.business.service;

import org.springframework.stereotype.Component;
import org.tourneytrack.impl.data.Competition;
import org.tourneytrack.impl.data.RuleSet;
import org.tourneytrack.impl.data.ScoreEntry;
import org.tourneytrack.intf.dto.data.CompetitionDto;
import org.tourneytrack.intf.dto.data.ScoreEntryDto;
import org.tourneytrack.intf.dto.request.CreateCompetitionRequest;
import org.tourneytrack.intf.dto.request.UpdateCompetitionRequest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CompetitionServiceImpl extends AbstractServiceBase implements CompetitionService {


    @Override
    public void createCompetition(CreateCompetitionRequest request) {
        validationService.validateDeadlineNotInPast(request.getDeadline());
        validationService.validateUserExists(request.getGameMasterId());
        validationService.validateRuleSetsExist(request.getRuleSetIds());

        Competition competition = competitionMapper.fromCreateCompetitionRequest(request);
        competition.setGameMaster(userDao.findById(request.getGameMasterId()).orElseThrow());
        competition.setRuleSets(
                request.getRuleSetIds().stream()
                        .map(id -> ruleSetDao.findById(id).orElseThrow())
                        .collect(Collectors.toList())
        );
        competitionDao.save(competition);
    }

    @Override
    public CompetitionDto getById(Long id) {
        Optional<Competition> competition = competitionDao.findById(id);
        if (competition.isPresent()) {
            return competitionMapper.toDto(competition.get());
        }
        return null;
    }

    @Override
    public List<CompetitionDto> listForUser(Long userId) {
        return competitionDao.findAllByUserId(userId).stream()
                .map(competitionMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public List<ScoreEntryDto> getScoreBoard(Long competitionId) {
        List<ScoreEntry> entries = scoreEntryDao.findAllByCompetitionId(competitionId);
        return entries.stream().map(scoreEntryMapper::toDto).collect(Collectors.toList());
    }


    @Override
    public void assignRuleSet(Long competitionId, Long ruleSetId) {
        Competition competition = competitionDao.findById(competitionId).orElseThrow();
        RuleSet ruleSet = ruleSetDao.findById(ruleSetId).orElseThrow();

        if (!competition.getRuleSets().contains(ruleSet)) {
            competition.getRuleSets().add(ruleSet);
            competitionDao.save(competition);
        }
    }

    @Override
    public void removeRuleSet(Long competitionId, Long ruleSetId) {
        Competition competition = competitionDao.findById(competitionId).orElseThrow();
        competition.getRuleSets().removeIf(rs -> rs.getId().equals(ruleSetId));
        competitionDao.save(competition);
    }


    @Override
    public void stopCompetition(Long competitionId) {
        Competition comp = validationService.validateCompetitionExists(competitionId);
        comp.setInProgress(false);
        competitionDao.save(comp);
    }

    @Override
    public void update(Long id, UpdateCompetitionRequest request) {
        Competition comp = validationService.validateCompetitionExists(id);
        if (request.getDeadline() != null) validationService.validateDeadlineNotInPast(request.getDeadline());

        if (request.getName() != null) comp.setName(request.getName());
        if (request.getDescription() != null) comp.setDescription(request.getDescription());
        if (request.getDeadline() != null) comp.setDeadline(request.getDeadline());

        competitionDao.save(comp);
    }


    @Override
    public void delete(Long id) {
        validationService.validateCompetitionExists(id);
        competitionDao.deleteById(id);
    }

    @Override
    public void joinCompetition(Long competitionId, Long userId) {
        Competition comp = validationService.validateCompetitionExists(competitionId);
        validationService.validateUserExists(userId);
        validationService.validateCompetitionIsOpen(comp);
        validationService.validateUserNotAlreadyJoined(comp, userId);

        comp.getParticipants().add(userDao.findById(userId).orElseThrow());
        competitionDao.save(comp);
    }

    @Override
    public void leaveCompetition(Long competitionId, Long userId) {
        Competition comp = validationService.validateCompetitionExists(competitionId);
        validationService.validateUserExists(userId);
        validationService.validateUserJoined(comp, userId);

        comp.getParticipants().removeIf(u -> u.getId().equals(userId));
        competitionDao.save(comp);
    }

    @Override
    public List<CompetitionDto> listJoinedCompetitions(Long userId) {
        return competitionDao.findAllByUserId(userId).stream()
                .map(competitionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isParticipant(Long competitionId, Long userId) {
        Competition competition = competitionDao.findById(competitionId).orElseThrow();
        return competition.getParticipants().stream()
                .anyMatch(user -> user.getId().equals(userId));
    }

    @Override
    public List<CompetitionDto> listAll() {
        return competitionDao.findAll().stream()
                .map(competitionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CompetitionDto> listByGameMaster(Long gameMasterId) {
        return competitionDao.findAllByGameMasterId(gameMasterId).stream()
                .map(competitionMapper::toDto)
                .collect(Collectors.toList());
    }


}
