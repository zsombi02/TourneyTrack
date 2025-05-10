package org.tourneytrack.intf.dto.data;

import lombok.Data;

import java.util.List;

@Data
public class ScoreBoardDto {
    private List<ScoreEntryDto> entries;
}