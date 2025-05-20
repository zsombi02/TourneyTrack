package org.tourneytrack.impl.dao;

import org.tourneytrack.impl.data.Competition;

import java.util.List;
import java.util.Optional;

public interface CompetitionDao {
    Competition save(Competition competition);
    Optional<Competition> findById(Long id);
    List<Competition> findAllByUserId(Long userId);
    void deleteById(Long id);
    List<Competition> findAll();
    List<Competition> findAllByGameMasterId(Long gameMasterId);

}