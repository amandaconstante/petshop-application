package com.app.petshop.service;

import com.app.petshop.domain.PermissionType;
import com.app.petshop.domain.User;
import com.app.petshop.dto.UserDto;
import com.app.petshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TutorService tutorService;

    public void createUser(UserDto userDto) {
   //     validate(userDto);
        User user = new User();
        user.setName(userDto.name());
        user.setBirthDate(userDto.birthDate());
        user.setEmail(userDto.email());
        user.setPermission(userDto.permission());
        user.setPassword(getHashedPassword(userDto.password()));
        userRepository.save(user);

        if (userDto.permission().equals(PermissionType.COMMOM)) {
            // TO-DO:
            // chama método para criar o tutor no TutorService (injetar aqui)
            // site terá cadastro apenas do usuário comum como um site padrão
            // para cadastrar um usuário ADMIN: terá qeu ter um usuário ADMIM logado para aparecer opção
            // no front terá essa distinção;
            tutorService.createTutor(userDto);
        }
    }

    private String getHashedPassword(String password) {
        BCryptPasswordEncoder encript = new BCryptPasswordEncoder();
        return encript.encode(password);
    }

    private void validate(UserDto userDto) {
        if (userRepository.findByEmail(userDto.email()).isPresent()) {
            throw new IllegalArgumentException("\n\n\nEmail já cadastrado!\n\n\n");
        }
    }
}
