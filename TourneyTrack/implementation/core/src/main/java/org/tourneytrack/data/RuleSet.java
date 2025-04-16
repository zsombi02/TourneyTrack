package org.tourneytrack.data;

import lombok.Data;

import java.util.List;

@Data
public class RuleSet {
    private Long id;
    private String name;
    private List<Rule> rules;
}