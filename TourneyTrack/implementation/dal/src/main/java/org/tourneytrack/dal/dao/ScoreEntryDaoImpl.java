package org.tourneytrack.dal.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.tourneytrack.dal.entity.ScoreEntryEntity;
import org.tourneytrack.dal.mapper.ScoreEntryEntityMapper;
import org.tourneytrack.dal.repository.ScoreEntryRepository;
import org.tourneytrack.impl.dao.ScoreEntryDao;
import org.tourneytrack.impl.data.ScoreEntry;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ScoreEntryDaoImpl implements ScoreEntryDao {

    private final ScoreEntryRepository repository;
    private final ScoreEntryEntityMapper mapper;

    @Override
    public void save(ScoreEntry entry) {
        ScoreEntryEntity entity = mapper.toEntity(entry);
        repository.save(entity);
    }

    @Override
    public List<ScoreEntry> findAllByCompetitionId(Long competitionId) {
        return repository.findAllByCompetitionId(competitionId)
                .stream()
                .map(mapper::toModel)
                .collect(Collectors.toList());
    }

}
