package org.tourneytrack.impl.business.mapper;

import org.springframework.stereotype.Component;
import org.tourneytrack.impl.data.ScoreBoard;
import org.tourneytrack.impl.data.ScoreEntry;
import org.tourneytrack.intf.dto.data.ScoreBoardDto;
import org.tourneytrack.intf.dto.data.ScoreEntryDto;
import org.tourneytrack.intf.dto.data.RuleDto;
import org.tourneytrack.intf.dto.data.UserDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ScoreBoardMapper {

    public ScoreBoardDto toDto(ScoreBoard model) {
        ScoreBoardDto dto = new ScoreBoardDto();

        List<ScoreEntryDto> entries = model.getEntries().stream().map(entry -> {
            ScoreEntryDto e = new ScoreEntryDto();
            e.setPointsEarned(entry.getPointsEarned());

            UserDto user = new UserDto();
            user.setId(entry.getPlayer().getId());
            user.setName(entry.getPlayer().getName());
            user.setEmail(entry.getPlayer().getEmail());
            user.setType(entry.getPlayer().getType().name());
            e.setPlayer(user);

            RuleDto rule = new RuleDto();
            rule.setId(entry.getRule().getId());
            rule.setName(entry.getRule().getName());
            rule.setDescription(entry.getRule().getDescription());
            rule.setPoints(entry.getRule().getPoints());
            rule.setRepetitions(entry.getRule().getRepetitions());
            e.setRule(rule);

            return e;
        }).collect(Collectors.toList());

        dto.setEntries(entries);
        return dto;
    }
}
