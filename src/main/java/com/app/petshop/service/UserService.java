package com.app.petshop.service;

import com.app.petshop.domain.PermissionType;
import com.app.petshop.domain.User;
import com.app.petshop.dto.UserRequestDto;
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

    public User createUser(UserRequestDto userRequestDto) {
   //     validate(userRequestDto);
        User user = new User();
        user.setName(userRequestDto.name());
        user.setBirthDate(userRequestDto.birthDate());
        user.setEmail(userRequestDto.email());
        user.setPermission(userRequestDto.permission());
        user.setPassword(getHashedPassword(userRequestDto.password()));

        return userRepository.save(user);
    }
    /*
    *       if (userRequestDto.permission().equals(PermissionType.COMMOM)) {
            // TO-DO:
            // chama método para criar o tutor no TutorService (injetar aqui)
            // site terá cadastro apenas do usuário comum como um site padrão
            // para cadastrar um usuário ADMIN: terá qeu ter um usuário ADMIM logado para aparecer opção
            // no front terá essa distinção;
            tutorService.createTutor(userRequestDto);
        }
    * */
    private String getHashedPassword(String password) {
        BCryptPasswordEncoder encript = new BCryptPasswordEncoder();
        return encript.encode(password);
    }

    private void validate(UserRequestDto userRequestDto) {
        if (userRepository.findByEmail(userRequestDto.email()).isPresent()) {
            throw new IllegalArgumentException("\n\n\nEmail já cadastrado!\n\n\n");
        }
    }
}
