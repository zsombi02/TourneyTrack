package org.tourneytrack.impl.data;

import lombok.Data;

@Data
public class ScoreEntry {
    private User player;
    private Rule rule;
    private int pointsEarned;

    private Long scoreBoardId;

}