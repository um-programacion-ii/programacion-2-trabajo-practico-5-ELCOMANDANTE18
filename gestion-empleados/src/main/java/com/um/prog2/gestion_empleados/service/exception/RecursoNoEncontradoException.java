package com.um.prog2.gestion_empleados.service.exception;

/**
 * Excepción lanzada cuando un recurso (Empleado, Departamento, Proyecto, etc.)
 * buscado por ID o nombre no se encuentra en el sistema.
 * Extiende RuntimeException para que no sea necesario declararla en la firma del método (unchecked).
 */
public class RecursoNoEncontradoException extends RuntimeException {

    // Constructor que acepta el mensaje de error
    public RecursoNoEncontradoException(String message) {
        super(message);
    }
}