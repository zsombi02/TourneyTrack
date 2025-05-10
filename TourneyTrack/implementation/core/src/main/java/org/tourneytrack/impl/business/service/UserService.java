package org.tourneytrack.impl.business.service;

import org.tourneytrack.intf.dto.data.UserDto;
import org.tourneytrack.intf.dto.request.RegisterUserRequest;

import java.util.List;

public interface UserService {
    UserDto register(RegisterUserRequest request);
    UserDto getById(Long id);
    List<UserDto> listAll();
}
