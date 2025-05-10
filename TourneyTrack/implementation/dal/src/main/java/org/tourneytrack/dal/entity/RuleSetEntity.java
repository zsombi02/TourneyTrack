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

    @OneToMany(mappedBy = "ruleSet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RuleEntity> rules;

}
