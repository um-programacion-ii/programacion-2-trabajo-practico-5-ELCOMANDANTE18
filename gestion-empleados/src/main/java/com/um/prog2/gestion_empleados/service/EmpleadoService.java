package com.um.prog2.gestion_empleados.service;

import com.um.prog2.gestion_empleados.model.Empleado;
import java.math.BigDecimal;
import java.util.List;

/**
 * Interfaz que define las operaciones de negocio para la gestión de Empleados.
 */
public interface EmpleadoService {

    // Operaciones CRUD básicas (y validación de email en guardar)
    Empleado guardar(Empleado empleado);

    Empleado buscarPorId(Long id);

    List<Empleado> obtenerTodos();

    Empleado actualizar(Long id, Empleado empleado);

    void eliminar(Long id);

    // Operaciones específicas del Caso de Uso CU-001 y CU-004: Consultas
    List<Empleado> buscarPorDepartamento(String nombreDepartamento);

    List<Empleado> buscarPorRangoSalario(BigDecimal salarioMin, BigDecimal salarioMax);

    BigDecimal obtenerSalarioPromedioPorDepartamento(Long departamentoId);
}