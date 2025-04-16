package org.tourneytrack.data;

import lombok.Data;

@Data
public class ScoreEntry {
    private User player;
    private Rule rule;
    private int pointsEarned;
}