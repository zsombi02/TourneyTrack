package org.tourneytrack.dal.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.tourneytrack.dal.entity.CompetitionEntity;
import org.tourneytrack.dal.mapper.CompetitionEntityMapper;
import org.tourneytrack.dal.repository.CompetitionRepository;
import org.tourneytrack.impl.dao.CompetitionDao;
import org.tourneytrack.impl.data.Competition;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CompetitionDaoImpl implements CompetitionDao {
    private final CompetitionRepository repository;
    private final CompetitionEntityMapper mapper;

    @Override
    public Competition save(Competition competition) {
        CompetitionEntity entity = mapper.toEntity(competition);
            return mapper.toModel(repository.save(entity));
    }

    @Override
    public Optional<Competition> findById(Long id) {
        return repository.findById(id)
                         .map(mapper::toModel);
    }

    @Override
    public List<Competition> findAllByUserId(Long userId) {
        return repository.findAllByUserId(userId).stream()
                .map(mapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Competition> findAll() {
        return repository.findAll().stream()
                .map(mapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Competition> findAllByGameMasterId(Long gameMasterId) {
        return repository.findAllByGameMaster_Id(gameMasterId).stream()
                .map(mapper::toModel)
                .collect(Collectors.toList());
    }


}