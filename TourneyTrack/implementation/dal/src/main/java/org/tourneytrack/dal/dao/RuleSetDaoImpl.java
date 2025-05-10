package org.tourneytrack.dal.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.tourneytrack.dal.entity.RuleEntity;
import org.tourneytrack.dal.entity.RuleSetEntity;
import org.tourneytrack.dal.mapper.RuleSetEntityMapper;
import org.tourneytrack.dal.repository.RuleSetRepository;
import org.tourneytrack.impl.dao.RuleSetDao;
import org.tourneytrack.impl.data.RuleSet;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RuleSetDaoImpl implements RuleSetDao {

    private final RuleSetRepository repository;
    private final RuleSetEntityMapper mapper;

    @Override
    public RuleSet save(RuleSet ruleSet) {
        RuleSetEntity entity = mapper.toEntity(ruleSet);
        return mapper.toModel(repository.save(entity));
    }

    @Override
    public Optional<RuleSet> findById(Long id) {
        return repository.findById(id)
                .map(mapper::toModel);
    }

    @Override
    public List<RuleSet> findAll() {
        return repository.findAll()
                         .stream()
                         .map(mapper::toModel)
                         .collect(Collectors.toList());
    }
}
