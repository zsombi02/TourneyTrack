package org.tourneytrack.intf.dto.data;

import lombok.Data;

import java.util.Date;

@Data
public class SubmissionDto {
    private Long id;
    private Long competitionId;
    private UserDto user;
    private RuleDto rule;
    private Date submittedAt;
    private String description;
    private String status;
    private String reviewerComment;
}