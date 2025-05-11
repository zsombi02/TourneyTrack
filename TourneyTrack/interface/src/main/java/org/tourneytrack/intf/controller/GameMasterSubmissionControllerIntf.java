package org.tourneytrack.intf.controller;

import org.tourneytrack.intf.dto.request.ApproveSubmissionRequest;

public interface GameMasterSubmissionControllerIntf {

    void approveSubmission(Long id, ApproveSubmissionRequest request);
}
