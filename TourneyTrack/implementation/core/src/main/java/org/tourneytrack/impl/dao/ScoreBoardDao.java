package org.tourneytrack.impl.dao;

import org.tourneytrack.impl.data.ScoreBoard;

public interface ScoreBoardDao {
    ScoreBoard createEmpty();
    ScoreBoard findById(Long id);
    ScoreBoard save(ScoreBoard board);
}