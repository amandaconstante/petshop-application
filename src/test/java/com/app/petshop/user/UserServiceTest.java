package com.app.petshop;

import com.app.petshop.domain.user.User;
import com.app.petshop.dto.UserRequestDto;
import com.app.petshop.repository.UserRepository;
import com.app.petshop.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("Success = Should create a new user with success.")
    void shouldCreateUser() {
        UserRequestDto userInput = UserFactory.createUserRequestDto();

        when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(UserFactory.createUser(userInput));

        User userCreated = userService.createUser(userInput);

        assertThat(userCreated.getName()).isEqualTo("Mocala");
        assertThat(userCreated.getLogin()).isEqualTo("mockadinho");
        assertNotNull(userCreated.getId());
        assertEquals(10L, userCreated.getId());
    }

}
