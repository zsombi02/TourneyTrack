package org.tourneytrack.dal.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "score_entries")
@Getter
@Setter
public class ScoreEntryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UserEntity player;

    @ManyToOne
    private RuleEntity rule;

    private int pointsEarned;

    @Column(name = "competition_id", nullable = false)
    private Long competitionId;


}
