package org.tourneytrack.impl.business.mapper;

import org.springframework.stereotype.Component;
import org.tourneytrack.impl.data.Rule;
import org.tourneytrack.impl.data.RuleSet;
import org.tourneytrack.intf.dto.data.RuleDto;
import org.tourneytrack.intf.dto.data.RuleSetDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RuleSetMapper {
    public RuleSetDto toDto(RuleSet model) {
        RuleSetDto dto = new RuleSetDto();
        dto.setId(model.getId());
        dto.setName(model.getName());

        List<RuleDto> rules = model.getRules().stream().map(rule -> {
            RuleDto r = new RuleDto();
            r.setId(rule.getId());
            r.setName(rule.getName());
            r.setDescription(rule.getDescription());
            r.setPoints(rule.getPoints());
            return r;
        }).collect(Collectors.toList());

        dto.setRules(rules);
        return dto;
    }
}
