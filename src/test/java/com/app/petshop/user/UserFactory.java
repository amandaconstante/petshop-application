package com.app.petshop;

import com.app.petshop.domain.user.UserRole;
import com.app.petshop.domain.user.User;
import com.app.petshop.dto.UserRequestDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;

public class UserFactory {

    public static User createUser(UserRequestDto userInput) {
        User mockUser = new User();
        mockUser.setId(10L);
        mockUser.setName(userInput.name());
        mockUser.setLogin(userInput.login());
        mockUser.setEmail(userInput.email());
        mockUser.setBirthDate(userInput.birthDate());
        mockUser.setPassword(new BCryptPasswordEncoder().encode(userInput.password()));
        mockUser.setPermission(userInput.permission());
        return mockUser;
    }

    public static UserRequestDto createUserRequestDto() {
        return new UserRequestDto(
                "Mocala",
                "mockadinho",
                LocalDate.now(),
                "a@b.com",
                "123",
                UserRole.COMMOM
        );
    }

}
