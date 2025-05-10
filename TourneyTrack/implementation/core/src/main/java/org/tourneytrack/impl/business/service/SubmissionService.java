package org.tourneytrack.impl.business.service;

import org.springframework.stereotype.Component;
import org.tourneytrack.intf.dto.data.SubmissionDto;
import org.tourneytrack.intf.dto.request.ApproveSubmissionRequest;
import org.tourneytrack.intf.dto.request.SubmitScoreRequest;

import java.util.List;


public interface SubmissionService {
    void submitScore(SubmitScoreRequest request);
    List<SubmissionDto> getByCompetition(Long competitionId);
    void handleApproval(Long id, ApproveSubmissionRequest request);
    List<SubmissionDto> getByUser(Long userId);
}
