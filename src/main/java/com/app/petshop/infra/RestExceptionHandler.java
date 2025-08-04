package com.app.petshop.infra;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    private ResponseEntity<ErrorResponseDto> emailDuplicado(DataIntegrityViolationException exception) {
        System.out.println("\n\n\n*****************\n\n\n");
        System.out.println(">>>> ENTROU NO HANDLER <<<<");
        System.out.println("\n\n\n*****************\n\n\n");
        Throwable rootCause = exception.getMostSpecificCause();
        String rootMsg = rootCause.getMessage();

        if (rootMsg != null && rootMsg.contains("user_tb") && rootMsg.contains("Unique index or primary key violation")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    new ErrorResponseDto("E-mail já cadastrado.", HttpStatus.CONFLICT)
            );
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ErrorResponseDto("Violação da integridade dos dados.", HttpStatus.BAD_REQUEST)
        );
    }
}
