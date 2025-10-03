package com.app.petshop.controller;

import com.app.petshop.domain.user.User;
import com.app.petshop.dto.UserRequestDto;
import com.app.petshop.dto.UserResponseDto;
import com.app.petshop.repository.UserRepository;
import com.app.petshop.service.UserService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userRepository.findById(id);
    }

}
