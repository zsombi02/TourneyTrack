package org.tourneytrack.data;

import lombok.Data;

@Data
public class Rule {
    private Long id;
    private String name;
    private String description;
    private int points;
    private int repetitions;
}