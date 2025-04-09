package br.com.daniel.testerevenda.controller.exceptions;

import br.com.daniel.testerevenda.exceptions.CustomerNotException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ControllerExceptionHandler {

    private record StandardError(
            LocalDateTime timestamp,
            int status,
            String error,
            String message,
            String path){
    }

    @ExceptionHandler(CustomerNotException.class)
    ResponseEntity<StandardError> handlerNotFoundException(final CustomerNotException ex, final HttpServletRequest request){
        return ResponseEntity.status(NOT_FOUND).body(
                new StandardError(
                        LocalDateTime.now(),
                        NOT_FOUND.value(),
                        NOT_FOUND.getReasonPhrase(),
                        ex.getMessage(),
                        request.getRequestURI()
                )
        );
    }
}