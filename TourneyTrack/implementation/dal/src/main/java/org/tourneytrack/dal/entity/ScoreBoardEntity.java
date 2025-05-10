package org.tourneytrack.dal.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "score_boards")
@Getter
@Setter
public class ScoreBoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "scoreboard_id", referencedColumnName = "id", insertable = false, updatable = false)
    private List<ScoreEntryEntity> entries;


}
