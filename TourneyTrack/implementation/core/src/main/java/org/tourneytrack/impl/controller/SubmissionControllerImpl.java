package org.tourneytrack.impl.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tourneytrack.impl.business.service.SubmissionService;
import org.tourneytrack.intf.controller.SubmissionController;
import org.tourneytrack.intf.dto.data.SubmissionDto;
import org.tourneytrack.intf.dto.request.ApproveSubmissionRequest;
import org.tourneytrack.intf.dto.request.SubmitScoreRequest;

import java.util.List;

@RestController
@RequestMapping("/api/submissions")
@RequiredArgsConstructor
public class SubmissionControllerImpl implements SubmissionController {

    @Autowired
    private final SubmissionService submissionService;

    @PostMapping
    public void submitScore(@RequestBody SubmitScoreRequest request) {
        this.submissionService.submitScore(request);
    }

    @GetMapping("/by-competition/{competitionId}")
    public List<SubmissionDto> getSubmissionsForCompetition(@PathVariable Long competitionId) {
        return this.submissionService.getByCompetition(competitionId);
    }

    @PostMapping("/{id}/approve")
    public void approveSubmission(@PathVariable Long id, @RequestBody ApproveSubmissionRequest request) {
        this.submissionService.handleApproval(id, request);
    }

    @Override
    public List<SubmissionDto> getSubmissionsForUser(Long userId) {
        return submissionService.getByUser(userId);
    }


}