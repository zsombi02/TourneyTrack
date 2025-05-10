package org.tourneytrack.dal.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "competitions")
@Getter
@Setter
public class CompetitionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "game_master_id")
    private UserEntity gameMaster;

    @ManyToMany
    @JoinTable(
            name = "competition_participants",
            joinColumns = @JoinColumn(name = "competition_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<UserEntity> participants;

    private Date deadline;
    private boolean inProgress;

    @ManyToMany
    @JoinTable(
            name = "competition_rule_sets",
            joinColumns = @JoinColumn(name = "competition_id"),
            inverseJoinColumns = @JoinColumn(name = "ruleset_id")
    )
    private List<RuleSetEntity> ruleSets;

    @Column(name = "scoreboard_id")
    private Long scoreBoardId;

}