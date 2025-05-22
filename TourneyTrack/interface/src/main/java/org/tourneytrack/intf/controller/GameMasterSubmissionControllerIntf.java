package org.tourneytrack.intf.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tourneytrack.intf.dto.request.ApproveSubmissionRequest;

@RequestMapping("/api/master/submissions")
public interface GameMasterSubmissionControllerIntf {

    @PostMapping("/{id}/approve")
    void approveSubmission(@PathVariable Long id, @RequestBody ApproveSubmissionRequest request);
}
