package com.app.petshop.service;

import com.app.petshop.dto.UserRequestDto;
import org.springframework.stereotype.Service;

@Service
public class TutorService {

    public void createTutor(UserRequestDto user) {
        System.out.println("Chamando método de criação do tutor...");
    }
}
