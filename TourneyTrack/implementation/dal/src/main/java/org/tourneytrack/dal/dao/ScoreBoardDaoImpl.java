package org.tourneytrack.dal.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.tourneytrack.dal.entity.ScoreBoardEntity;
import org.tourneytrack.dal.entity.ScoreEntryEntity;
import org.tourneytrack.dal.mapper.ScoreBoardEntityMapper;
import org.tourneytrack.dal.repository.ScoreBoardRepository;
import org.tourneytrack.dal.repository.ScoreEntryRepository;
import org.tourneytrack.impl.dao.ScoreBoardDao;
import org.tourneytrack.impl.data.ScoreBoard;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ScoreBoardDaoImpl implements ScoreBoardDao {

    private final ScoreBoardRepository repository;
    private final ScoreEntryRepository entryRepository;
    private final ScoreBoardEntityMapper mapper;

    @Override
    public ScoreBoard createEmpty() {
        ScoreBoard board = new ScoreBoard();
        board.setEntries(new java.util.ArrayList<>());
        return save(board);
    }

    @Override
    public ScoreBoard findById(Long id) {
        ScoreBoardEntity entity = repository.findById(id).orElseThrow();

        List<ScoreEntryEntity> entries = entryRepository.findAllByScoreBoardId(id);
        entity.setEntries(entries);

        return mapper.toModel(entity);
    }

    @Override
    public ScoreBoard save(ScoreBoard board) {
        ScoreBoardEntity entity = mapper.toEntity(board);
        return mapper.toModel(repository.save(entity));
    }
}
