package org.tourneytrack.intf.dto.data;

import lombok.Data;

import java.util.List;

@Data
public class RuleSetDto {
    private Long id;
    private String name;
    private List<RuleDto> rules;
}