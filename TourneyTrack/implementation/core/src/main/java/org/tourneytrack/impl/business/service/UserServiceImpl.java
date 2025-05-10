package org.tourneytrack.impl.business.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.tourneytrack.impl.business.mapper.UserMapper;
import org.tourneytrack.impl.dao.UserDao;
import org.tourneytrack.impl.data.User;
import org.tourneytrack.intf.dto.data.UserDto;
import org.tourneytrack.intf.dto.request.RegisterUserRequest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao dao;
    private final UserMapper mapper;

    @Override
    public UserDto register(RegisterUserRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setType(mapper.toModel(request.getType()));

        return mapper.toDto(dao.save(user));
    }

    @Override
    public UserDto getById(Long id) {
        Optional<User> user = dao.findById(id);
        return user.map(mapper::toDto).orElse(null);
    }

    @Override
    public List<UserDto> listAll() {
        return dao.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }
}
