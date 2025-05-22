package org.tourneytrack.intf.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tourneytrack.intf.dto.data.UserDto;
import org.tourneytrack.intf.dto.request.RegisterUserRequest;

@RequestMapping("/api/auth")
public interface AuthControllerIntf {

    @PostMapping("/register")
    void register(@RequestBody RegisterUserRequest request);

    @PostMapping("/login")
    ResponseEntity<?> login();

    @GetMapping("/me")
    ResponseEntity<UserDto> getCurrentUser(Authentication authentication);
}
