package org.tourneytrack.impl.dao;

import org.tourneytrack.impl.data.ScoreEntry;

import java.util.List;

public interface ScoreEntryDao {
    void save(ScoreEntry entry);
    List<ScoreEntry> findAllByCompetitionId(Long competitionId);

}
