package com.example.igorfv.product_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalHandleException {

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
    Map<String, Object> body = new HashMap<>();
    body.put("timestamp", LocalDateTime.now());
    body.put("status", HttpStatus.NOT_FOUND);
    body.put("erro", "Usuário não existente");
    body.put("message", ex.getMessage());

    return ResponseEntity.badRequest().body(ex.getMessage());
  }

    @ExceptionHandler(DuplicatedException.class)
    public ResponseEntity<Object> handleDuplicatedException(DuplicatedException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.UNPROCESSABLE_ENTITY);
        body.put("erro", "Usuário já existente");
        body.put("message", ex.getMessage());

        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
