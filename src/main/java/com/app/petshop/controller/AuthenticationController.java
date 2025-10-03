package com.app.petshop.controller;

import com.app.petshop.domain.user.AuthenticationDto;
import com.app.petshop.domain.user.User;
import com.app.petshop.dto.UserRequestDto;
import com.app.petshop.dto.UserResponseDto;
import com.app.petshop.repository.UserRepository;
import com.app.petshop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDto data) {
        var usernamePassowrd = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = authenticationManager.authenticate(usernamePassowrd);
        if(auth.isAuthenticated()) {
            System.out.println("Usuário autenticado!");

        } else {
            System.out.println("Usuário ou senha incorretos.");
        }

        return ResponseEntity.ok().build();
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponseDto> create(@RequestBody UserRequestDto userRequestDto) {
        User user = userService.createUser(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new UserResponseDto(
                        user.getId(), user.getName(), user.getLogin(), user.getBirthDate(), user.getEmail())
                );
    }
}
