package com.um.prog2.gestion_empleados.service.exception;

/**
 * Excepción lanzada cuando se intenta crear un empleado con un email que ya está
 * registrado en la base de datos, violando la restricción de unicidad.
 */
public class EmailDuplicadoException extends RuntimeException {

    // Constructor que acepta el mensaje de error
    public EmailDuplicadoException(String message) {
        super(message);
    }
}