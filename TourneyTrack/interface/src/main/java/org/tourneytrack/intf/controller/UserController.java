package org.tourneytrack.intf.controller;

import org.tourneytrack.intf.dto.data.UserDto;
import org.tourneytrack.intf.dto.request.RegisterUserRequest;

import java.util.List;

public interface UserController {
    UserDto registerUser(RegisterUserRequest request);
    UserDto getById(Long id);
    List<UserDto> listAll();
}
