package org.tourneytrack.intf.dto.request;

import lombok.Data;

@Data
public class SubmitScoreRequest {
    private Long competitionId;
    private Long userId;
    private Long ruleId;
    private String description;
}
