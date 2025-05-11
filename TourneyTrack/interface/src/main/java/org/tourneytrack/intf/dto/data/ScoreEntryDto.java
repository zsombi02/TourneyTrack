package org.tourneytrack.intf.dto.data;

import lombok.Data;

@Data
public class ScoreEntryDto {
    private UserDto player;
    private RuleDto rule;
    private int pointsEarned;
    private Long competitionId;
}