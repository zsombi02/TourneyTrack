package org.tourneytrack.intf.dto.request;

import lombok.Data;

@Data
public class ApproveSubmissionRequest {
    private boolean approved;
    private String reviewerComment;

}
