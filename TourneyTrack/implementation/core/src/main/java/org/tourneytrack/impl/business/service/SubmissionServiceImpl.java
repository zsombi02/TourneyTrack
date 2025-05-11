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
public class SubmissionServiceImpl extends AbstractServiceBase implements SubmissionService {


    @Override
    public void submitScore(SubmitScoreRequest request) {
        Competition comp = validationService.validateCompetitionExists(request.getCompetitionId());
        validationService.validateCompetitionIsOpen(comp);
        validationService.validateUserJoined(comp, request.getUserId());
        validationService.validateRuleExists(request.getRuleId());
        validationService.validateRuleIsPartOfCompetition(request.getRuleId(), comp);
        validationService.validateUserExists(request.getUserId());

        Submission submission = new Submission();
        submission.setUser(userDao.findById(request.getUserId()).orElseThrow());
        submission.setRule(ruleDao.getRuleById(request.getRuleId()));
        submission.setCompetitionId(request.getCompetitionId());
        submission.setDescription(request.getDescription());
        submission.setSubmittedAt(new Date());
        submission.setStatus(SubmissionStatus.PENDING);

        submissionDao.save(submission);
    }

    @Override
    public List<SubmissionDto> getByCompetition(Long competitionId) {
        return submissionDao.findByCompetitionId(competitionId).stream()
                .map(submissionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void handleApproval(Long id, ApproveSubmissionRequest request) {
        Submission submission = validationService.validateSubmissionExists(id);
        validationService.validateSubmissionPending(submission);

        submission.setStatus(request.isApproved() ? SubmissionStatus.APPROVED : SubmissionStatus.REJECTED);
        submission.setReviewerComment(request.getReviewerComment());
        submissionDao.save(submission);

        if (submission.getStatus() == SubmissionStatus.APPROVED) {
            ScoreEntry entry = new ScoreEntry();
            entry.setPlayer(submission.getUser());
            entry.setRule(submission.getRule());
            entry.setPointsEarned(submission.getRule().getPoints());
            entry.setCompetitionId(submission.getCompetitionId());
            scoreEntryDao.save(entry);
        }
    }

    @Override
    public List<SubmissionDto> getByUser(Long userId) {
        return submissionDao.findByUserId(userId).stream()
                .map(submissionMapper::toDto)
                .collect(Collectors.toList());
    }


}

