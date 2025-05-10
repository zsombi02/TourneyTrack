package org.tourneytrack.intf.controller;

import org.tourneytrack.intf.dto.data.SubmissionDto;
import org.tourneytrack.intf.dto.request.ApproveSubmissionRequest;
import org.tourneytrack.intf.dto.request.SubmitScoreRequest;

import java.util.List;

public interface SubmissionController {
    void submitScore(SubmitScoreRequest request);
    List<SubmissionDto> getSubmissionsForCompetition(Long competitionId);
    void approveSubmission(Long submissionId, ApproveSubmissionRequest request);
    List<SubmissionDto> getSubmissionsForUser(Long userId);

}