package com.um.prog2.gestion_empleados.controller.handler;

import com.um.prog2.gestion_empleados.service.exception.EmailDuplicadoException;
import com.um.prog2.gestion_empleados.service.exception.RecursoNoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Maneja 404 Not Found para todos los *NoEncontradoException
    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<Map<String, String>> handleNotFoundException(RecursoNoEncontradoException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Recurso No Encontrado");
        error.put("mensaje", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND); // HTTP 404
    }

    // Maneja 409 Conflict para violaciones de unicidad como el email
    @ExceptionHandler(EmailDuplicadoException.class)
    public ResponseEntity<Map<String, String>> handleConflictException(EmailDuplicadoException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Conflicto de Datos");
        error.put("mensaje", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT); // HTTP 409
    }

    // Opcional: Manejo de errores de validación de Bean Validation (@Valid)
    // @ExceptionHandler(MethodArgumentNotValidException.class)
    // ... devuelve HTTP 400 BAD_REQUEST
}