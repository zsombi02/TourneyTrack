package org.tourneytrack.impl.data;

import lombok.Data;

import java.util.Date;

@Data
public class Submission {
    private Long id;

    private Long competitionId; // csak az ID kell itt, nem a teljes verseny
    private User user;          // teljes User objektum
    private Rule rule;          // teljes Rule objektum
    private String description;
    private Date submittedAt;

    private SubmissionStatus status;
    private String reviewerComment;
}