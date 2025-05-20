package org.tourneytrack.intf.dto.request;

import lombok.Data;

@Data
public class UpdateRuleRequest {
    private String name;
    private String description;
    private int points;
}
