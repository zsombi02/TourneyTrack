package org.tourneytrack.impl.business.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.tourneytrack.impl.business.mapper.SubmissionMapper;
import org.tourneytrack.impl.dao.*;
import org.tourneytrack.impl.data.*;
import org.tourneytrack.intf.dto.data.SubmissionDto;
import org.tourneytrack.intf.dto.request.ApproveSubmissionRequest;
import org.tourneytrack.intf.dto.request.SubmitScoreRequest;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SubmissionServiceImpl implements SubmissionService {

    private final SubmissionDao dao;
    private final SubmissionMapper mapper;

    private final ScoreBoardDao scoreBoardDao;
    private final CompetitionDao competitionDao;
    private final UserDao userDao;
    private final RuleDao ruleDao;
    private final ScoreEntryDao scoreEntryDao;


    @Override
    public void submitScore(SubmitScoreRequest request) {
        Submission submission = new Submission();

        User user = userDao.findById(request.getUserId()).orElseThrow();
        submission.setUser(user);

        Rule rule = ruleDao.getRuleById(request.getRuleId());
        submission.setRule(rule);

        submission.setCompetitionId(request.getCompetitionId());
        submission.setDescription(request.getDescription());
        submission.setSubmittedAt(new Date());
        submission.setStatus(SubmissionStatus.PENDING);

        dao.save(submission);
    }


    @Override
    public List<SubmissionDto> getByCompetition(Long competitionId) {
        return dao.findByCompetitionId(competitionId).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void handleApproval(Long id, ApproveSubmissionRequest request) {
        Submission submission = dao.findById(id).orElseThrow();

        submission.setStatus(request.isApproved() ? SubmissionStatus.APPROVED : SubmissionStatus.REJECTED);
        submission.setReviewerComment(request.getReviewerComment());
        dao.save(submission);

        if (submission.getStatus() == SubmissionStatus.APPROVED) {
            Competition competition = competitionDao.findById(submission.getCompetitionId()).orElseThrow();

            ScoreEntry entry = new ScoreEntry();
            entry.setPlayer(submission.getUser());
            entry.setRule(submission.getRule());
            entry.setPointsEarned(submission.getRule().getPoints());
            entry.setScoreBoardId(competition.getScoreBoardId());

            // 👉 KELL hozzá ScoreEntryDao
            scoreEntryDao.save(entry);
        }
    }

    @Override
    public List<SubmissionDto> getByUser(Long userId) {
        return dao.findByUserId(userId).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }


}

