package org.tourneytrack.dal.entity;

import lombok.Getter;
import lombok.Setter;
import org.tourneytrack.impl.data.SubmissionStatus;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "submissions")
@Getter
@Setter
public class SubmissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long competitionId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "rule_id")
    private RuleEntity rule;

    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date submittedAt;

    @Enumerated(EnumType.STRING)
    private SubmissionStatus status;

    private String reviewerComment;
}
