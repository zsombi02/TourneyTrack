package org.tourneytrack.data;

import lombok.Data;

import java.util.List;

@Data
public class ScoreBoard {
    private List<ScoreEntry> entries;
}