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
public class UserServiceImpl extends AbstractServiceBase implements UserService {


    @Override
    public UserDto register(RegisterUserRequest request) {
        validationService.validateRegisterFields(request);
        validationService.validateEmailNotUsed(request.getEmail());

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setType(userMapper.toModel(request.getType()));

        return userMapper.toDto(userDao.save(user));
    }

    @Override
    public UserDto getById(Long id) {
        return userMapper.toDto(validationService.validateUserExistsById(id));
    }

    @Override
    public List<UserDto> listAll() {
        return userDao.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getByEmail(String email) {
        return userDao.findByEmail(email)
                .map(userMapper::toDto)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }

}
