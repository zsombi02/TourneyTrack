package org.tourneytrack.intf.dto.data;

import lombok.Data;

@Data
public class RuleDto {
    private Long id;
    private String name;
    private String description;
    private int points;
}