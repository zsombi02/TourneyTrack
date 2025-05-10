package org.tourneytrack.intf.dto.request;

import lombok.Data;

@Data
public class CreateRuleRequest {
    private String name;
    private String description;
    private int points;
    private int repetitions;
}
