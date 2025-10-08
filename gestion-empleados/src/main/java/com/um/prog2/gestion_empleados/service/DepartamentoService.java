package com.um.prog2.gestion_empleados.service;

import com.um.prog2.gestion_empleados.model.Departamento;

import java.math.BigDecimal;
import java.util.List;

/**
 * Interfaz que define las operaciones de negocio para la gestión de Departamentos.
 */
public interface DepartamentoService {

    // Operaciones CRUD básicas
    Departamento guardar(Departamento departamento);

    Departamento buscarPorId(Long id);

    List<Departamento> obtenerTodos();

    Departamento actualizar(Long id, Departamento departamento);

    void eliminar(Long id);

    // Operación específica del Caso de Uso CU-002: Reportes
    BigDecimal obtenerSalarioPromedio(Long departamentoId);
}
