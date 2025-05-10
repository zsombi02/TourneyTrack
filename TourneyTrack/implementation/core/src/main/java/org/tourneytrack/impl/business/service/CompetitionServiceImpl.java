package org.tourneytrack.impl.business.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.tourneytrack.impl.business.mapper.CompetitionMapper;
import org.tourneytrack.impl.business.mapper.ScoreBoardMapper;
import org.tourneytrack.impl.dao.CompetitionDao;
import org.tourneytrack.impl.dao.RuleSetDao;
import org.tourneytrack.impl.dao.ScoreBoardDao;
import org.tourneytrack.impl.dao.UserDao;
import org.tourneytrack.impl.data.*;
import org.tourneytrack.intf.dto.data.CompetitionDto;
import org.tourneytrack.intf.dto.data.ScoreBoardDto;
import org.tourneytrack.intf.dto.request.CreateCompetitionRequest;
import org.tourneytrack.intf.dto.request.UpdateCompetitionRequest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CompetitionServiceImpl implements CompetitionService {

    private final CompetitionMapper competitionMapper;

    private final ScoreBoardMapper scoreBoardMapper;

    private final CompetitionDao dao;

    private final RuleSetDao ruleSetDao;

    private final ScoreBoardDao scoreBoardDao;

    private final UserDao userDao;


    @Override
    public void createCompetition(CreateCompetitionRequest request) {


        Competition competition = competitionMapper.fromCreateCompetitionRequest(request);

        User gameMaster = userDao.findById(request.getGameMasterId()).orElseThrow();
        competition.setGameMaster(gameMaster);

        ScoreBoard newBoard = scoreBoardDao.createEmpty();
        competition.setScoreBoardId(newBoard.getId());

        List<RuleSet> ruleSets = request.getRuleSetIds().stream()
                .map(id -> ruleSetDao.findById(id).orElseThrow())
                .collect(Collectors.toList());

        competition.setRuleSets(ruleSets);

        dao.save(competition);
        System.out.println("Create competition: " + competition);
    }

    @Override
    public CompetitionDto getById(Long id) {
        Optional<Competition> competition = dao.findById(id);
        if (competition.isPresent()) {
            return competitionMapper.toDto(competition.get());
        }
        return null;
    }

    @Override
    public List<CompetitionDto> listForUser(Long userId) {
        return dao.findAllByUserId(userId).stream()
                .map(competitionMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public ScoreBoardDto getScoreBoard(Long competitionId) {
        Competition competition = dao.findById(competitionId).orElseThrow();
        return scoreBoardMapper.toDto(scoreBoardDao.findById(competition.getScoreBoardId()));
    }

    @Override
    public void assignRuleSet(Long competitionId, Long ruleSetId) {
        Competition competition = dao.findById(competitionId).orElseThrow();
        RuleSet ruleSet = ruleSetDao.findById(ruleSetId).orElseThrow();

        if (!competition.getRuleSets().contains(ruleSet)) {
            competition.getRuleSets().add(ruleSet);
            dao.save(competition);
        }
    }

    @Override
    public void removeRuleSet(Long competitionId, Long ruleSetId) {
        Competition competition = dao.findById(competitionId).orElseThrow();
        competition.getRuleSets().removeIf(rs -> rs.getId().equals(ruleSetId));
        dao.save(competition);
    }


    @Override
    public void stopCompetition(Long competitionId) {
        Competition competition = dao.findById(competitionId).orElseThrow();
        competition.setInProgress(false);
        dao.save(competition);
    }

    @Override
    public void update(Long id, UpdateCompetitionRequest request) {
        Competition competition = dao.findById(id).orElseThrow();

        // Csak akkor állítjuk, ha jön új érték
        if (request.getName() != null) {
            competition.setName(request.getName());
        }

        if (request.getDescription() != null) {
            competition.setDescription(request.getDescription());
        }

        if (request.getDeadline() != null) {
            competition.setDeadline(request.getDeadline());
        }

        if (request.getRuleSetIds() != null) {
            List<RuleSet> ruleSets = request.getRuleSetIds().stream()
                    .map(idRs -> ruleSetDao.findById(idRs).orElseThrow())
                    .collect(Collectors.toList());
            competition.setRuleSets(ruleSets);
        }

        dao.save(competition);
    }


    @Override
    public void delete(Long id) {
        dao.deleteById(id);
    }


}
