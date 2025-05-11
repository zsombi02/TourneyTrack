package org.tourneytrack.intf.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.tourneytrack.intf.dto.data.UserDto;
import org.tourneytrack.intf.dto.request.RegisterUserRequest;

public interface AuthControllerIntf {

    void register(RegisterUserRequest request);

    ResponseEntity<?> login();

    ResponseEntity<UserDto> getCurrentUser(Authentication authentication);
}
