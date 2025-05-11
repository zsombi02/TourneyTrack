package org.tourneytrack.dal.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.tourneytrack.dal.entity.UserEntity;
import org.tourneytrack.dal.mapper.UserEntityMapper;
import org.tourneytrack.dal.repository.UserRepository;
import org.tourneytrack.impl.dao.UserDao;
import org.tourneytrack.impl.data.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {

    private final UserRepository repository;
    private final UserEntityMapper mapper;

    @Override
    public User save(User user) {
        UserEntity entity = mapper.toEntity(user);
        return mapper.toModel(repository.save(entity));
    }

    @Override
    public Optional<User> findById(Long id) {
        return repository.findById(id).map(mapper::toModel);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll().stream().map(mapper::toModel).collect(Collectors.toList());
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email)
                .map(mapper::toModel);
    }


}
