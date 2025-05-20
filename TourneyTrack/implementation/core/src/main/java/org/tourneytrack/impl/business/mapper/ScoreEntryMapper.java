package org.tourneytrack.impl.business.mapper;

import org.springframework.stereotype.Component;
import org.tourneytrack.impl.data.ScoreEntry;
import org.tourneytrack.intf.dto.data.ScoreEntryDto;
import org.tourneytrack.intf.dto.data.RuleDto;
import org.tourneytrack.intf.dto.data.UserDto;

@Component
public class ScoreEntryMapper {

    public ScoreEntryDto toDto(ScoreEntry entry) {
        ScoreEntryDto dto = new ScoreEntryDto();
        dto.setPointsEarned(entry.getPointsEarned());

        UserDto user = new UserDto();
        user.setId(entry.getPlayer().getId());
        user.setName(entry.getPlayer().getName());
        user.setEmail(entry.getPlayer().getEmail());
        user.setType(entry.getPlayer().getType().name());
        dto.setPlayer(user);

        RuleDto rule = new RuleDto();
        rule.setId(entry.getRule().getId());
        rule.setName(entry.getRule().getName());
        rule.setDescription(entry.getRule().getDescription());
        rule.setPoints(entry.getRule().getPoints());
        dto.setRule(rule);

        dto.setCompetitionId(entry.getCompetitionId());

        return dto;
    }
}
