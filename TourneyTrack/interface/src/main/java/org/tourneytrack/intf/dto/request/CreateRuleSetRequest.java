package org.tourneytrack.intf.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class CreateRuleSetRequest {
    private String name;
    private List<CreateRuleRequest> rules;

}
