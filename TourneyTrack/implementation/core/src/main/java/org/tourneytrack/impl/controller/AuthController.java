package org.tourneytrack.impl.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.tourneytrack.intf.controller.AuthControllerIntf;
import org.tourneytrack.intf.dto.data.UserDto;
import org.tourneytrack.intf.dto.request.RegisterUserRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthController extends AbstractController implements AuthControllerIntf {

    @PostMapping("/register")
    public void register(@RequestBody RegisterUserRequest request) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        userService.register(request);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login() {
        return ResponseEntity.ok("Logged in");
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getCurrentUser(Authentication authentication) {
        String email = authentication.getName();
        UserDto dto = userService.getByEmail(email);
        return ResponseEntity.ok(dto);
    }

}
