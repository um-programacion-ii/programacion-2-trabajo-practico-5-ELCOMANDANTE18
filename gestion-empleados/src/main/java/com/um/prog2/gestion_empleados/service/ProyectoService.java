package com.um.prog2.gestion_empleados.service;

import com.um.prog2.gestion_empleados.model.Proyecto;
import java.util.List;

/**
 * Interfaz que define las operaciones de negocio para la gestión de Proyectos (CU-003).
 */
public interface ProyectoService {

    // Operaciones CRUD básicas
    Proyecto guardar(Proyecto proyecto);

    Proyecto buscarPorId(Long id);

    List<Proyecto> obtenerTodos();

    Proyecto actualizar(Long id, Proyecto proyecto);

    void eliminar(Long id);

    // Operaciones específicas del Caso de Uso CU-003: Asignación y Seguimiento
    Proyecto asignarEmpleado(Long proyectoId, Long empleadoId);

    void desasignarEmpleado(Long proyectoId, Long empleadoId);

    /**
     * Filtra proyectos por estado.
     * @param estado Puede ser "ACTIVO" o "INACTIVO"
     * @return Lista de proyectos que cumplen con el estado.
     */
    List<Proyecto> filtrarProyectosPorEstado(String estado);
}