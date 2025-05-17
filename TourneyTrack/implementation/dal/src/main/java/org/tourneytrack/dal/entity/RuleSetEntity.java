package org.tourneytrack.dal.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "rule_sets")
@Getter
@Setter
public class RuleSetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "rule_set_rules", // join tábla neve
            joinColumns = @JoinColumn(name = "ruleset_id"),
            inverseJoinColumns = @JoinColumn(name = "rule_id")
    )
    private List<RuleEntity> rules;

}
