package org.tourneytrack.impl.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.tourneytrack.impl.business.service.UserService;
import org.tourneytrack.intf.controller.UserController;
import org.tourneytrack.intf.dto.data.UserDto;
import org.tourneytrack.intf.dto.request.RegisterUserRequest;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @PostMapping("/register")
    public UserDto registerUser(@RequestBody RegisterUserRequest request) {
        return userService.register(request);
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @GetMapping
    public List<UserDto> listAll() {
        return userService.listAll();
    }
}
