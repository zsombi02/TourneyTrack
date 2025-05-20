package org.tourneytrack.impl.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tourneytrack.impl.dao.*;
import org.tourneytrack.impl.data.*;
import org.tourneytrack.impl.error.ValidationException;
import org.tourneytrack.intf.dto.request.RegisterUserRequest;

import java.util.Date;
import java.util.List;

@Component
public class ValidationService {

    @Autowired
    protected CompetitionDao competitionDao;

    @Autowired
    protected RuleSetDao ruleSetDao;

    @Autowired
    protected RuleDao ruleDao;

    @Autowired
    protected UserDao userDao;

    @Autowired
    protected SubmissionDao submissionDao;

    @Autowired
    protected ScoreEntryDao scoreEntryDao;


    public User validateUserExists(Long userId) {
        return userDao.findById(userId)
                .orElseThrow(() -> new ValidationException("User not found: " + userId));
    }

    public Competition validateCompetitionExists(Long competitionId) {
        return competitionDao.findById(competitionId)
                .orElseThrow(() -> new ValidationException("Competition not found: " + competitionId));
    }

    public void validateCompetitionIsOpen(Competition competition) {
        if (!competition.isInProgress()) {
            throw new ValidationException("Competition is closed.");
        }
    }

    public Rule validateRuleExists(Long ruleId) {
        Rule rule = ruleDao.getRuleById(ruleId);
        if (rule == null) {
            throw new ValidationException("Rule not found: " + ruleId);
        }
        return rule;
    }

    public Submission validateSubmissionExists(Long submissionId) {
        return submissionDao.findById(submissionId)
                .orElseThrow(() -> new ValidationException("Submission not found: " + submissionId));
    }

    public void validateSubmissionPending(Submission submission) {
        if (submission.getStatus() != SubmissionStatus.PENDING) {
            throw new ValidationException("Submission is already reviewed.");
        }
    }

    public void validateDeadlineNotInPast(Date deadline) {
        if (deadline.before(new Date())) {
            throw new ValidationException("Deadline must be in the future.");
        }
    }

    public RuleSet validateRuleSetExists(Long ruleSetId) {
        return ruleSetDao.findById(ruleSetId)
                .orElseThrow(() -> new ValidationException("RuleSet not found: " + ruleSetId));
    }

    public void validateUserNotAlreadyJoined(Competition comp, Long userId) {
        boolean joined = comp.getParticipants().stream().anyMatch(u -> u.getId().equals(userId));
        if (joined) throw new ValidationException("User already joined.");
    }

    public void validateRuleSetsExist(List<Long> ruleSetIds) {
        for (Long id : ruleSetIds) {
            if (!ruleSetDao.existsById(id)) {
                throw new ValidationException("RuleSet not found: " + id);
            }
        }
    }

    public void validateUserJoined(Competition comp, Long userId) {
        boolean joined = comp.getParticipants().stream().anyMatch(u -> u.getId().equals(userId));
        if (!joined) throw new ValidationException("User is not a participant.");
    }

    public void validateRuleFields(String name, int points) {
        if (name == null || name.trim().isEmpty()) {
            throw new ValidationException("Rule name must not be empty.");
        }
        if (points < 0) {
            throw new ValidationException("Points must be ≥ 0.");
        }
    }

    public void validateRuleSetName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new ValidationException("RuleSet name must not be empty.");
        }
    }

    public void validateRuleNotUsedInSubmissions(Long ruleId) {
        if (submissionDao.existsByRuleId(ruleId)) {
            throw new ValidationException("Rule is already used in a submission and cannot be deleted.");
        }
    }

    public void validateRuleIsPartOfCompetition(Long ruleId, Competition competition) {
        boolean found = competition.getRuleSets().stream()
                .flatMap(rs -> rs.getRules().stream())
                .anyMatch(r -> r.getId().equals(ruleId));
        if (!found) {
            throw new ValidationException("Rule is not part of this competition.");
        }
    }

    public void validateRegisterFields(RegisterUserRequest request) {
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new ValidationException("Name must not be empty.");
        }
        if (request.getEmail() == null || request.getEmail().trim().isEmpty()) {
            throw new ValidationException("Email must not be empty.");
        }
        if (request.getPassword() == null || request.getPassword().trim().isEmpty()) {
            throw new ValidationException("Password must not be empty.");
        }
        if (request.getType() == null) {
            throw new ValidationException("User type must be provided.");
        }
    }

    public void validateEmailNotUsed(String email) {
        if (userDao.findByEmail(email).isPresent()) {
            throw new ValidationException("Email is already registered: " + email);
        }
    }

    public User validateUserExistsById(Long id) {
        return userDao.findById(id)
                .orElseThrow(() -> new ValidationException("User not found with id: " + id));
    }

    public void validateRuleSetNotUsedInCompetitions(Long ruleSetId) {
        List<Competition> all = competitionDao.findAll();
        boolean used = all.stream().anyMatch(comp ->
                comp.getRuleSets().stream().anyMatch(rs -> rs.getId().equals(ruleSetId)));
        if (used) {
            throw new ValidationException("Cannot delete: RuleSet is used in an active competition.");
        }
    }

}