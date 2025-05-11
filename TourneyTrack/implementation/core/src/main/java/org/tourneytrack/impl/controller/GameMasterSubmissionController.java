package org.tourneytrack.impl.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.tourneytrack.intf.controller.GameMasterSubmissionControllerIntf;
import org.tourneytrack.intf.dto.request.ApproveSubmissionRequest;

@RestController
@RequestMapping("/api/master/submissions")
@PreAuthorize("hasRole('GAME_MASTER')")
public class GameMasterSubmissionController extends AbstractController implements GameMasterSubmissionControllerIntf {

    @PostMapping("/{id}/approve")
    public void approveSubmission(@PathVariable Long id, @RequestBody ApproveSubmissionRequest request) {
        submissionService.handleApproval(id, request);
    }
}
