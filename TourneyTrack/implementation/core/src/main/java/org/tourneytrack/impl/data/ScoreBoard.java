package org.tourneytrack.impl.data;

import lombok.Data;

import java.util.List;

@Data
public class ScoreBoard {

    private Long id;
    private List<ScoreEntry> entries;
}