package com.um.prog2.gestion_empleados.service.impl;

import com.um.prog2.gestion_empleados.model.Departamento;
import com.um.prog2.gestion_empleados.repository.DepartamentoRepository;
import com.um.prog2.gestion_empleados.repository.EmpleadoRepository; // Necesario para el reporte de salario promedio
import com.um.prog2.gestion_empleados.service.DepartamentoService;
import com.um.prog2.gestion_empleados.service.exception.RecursoNoEncontradoException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class DepartamentoServiceImpl implements DepartamentoService {

    private final DepartamentoRepository departamentoRepository;
    private final EmpleadoRepository empleadoRepository;

    // Inyección de Repositories
    public DepartamentoServiceImpl(DepartamentoRepository departamentoRepository, EmpleadoRepository empleadoRepository) {
        this.departamentoRepository = departamentoRepository;
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    public Departamento guardar(Departamento departamento) {
        // Podrías añadir lógica de validación aquí, ej: nombre único
        return departamentoRepository.save(departamento);
    }

    @Override
    @Transactional(readOnly = true)
    public Departamento buscarPorId(Long id) {
        return departamentoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Departamento no encontrado con ID: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Departamento> obtenerTodos() {
        return departamentoRepository.findAll();
    }

    @Override
    public Departamento actualizar(Long id, Departamento departamento) {
        if (!departamentoRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("Departamento no encontrado con ID para actualizar: " + id);
        }
        departamento.setId(id);
        return departamentoRepository.save(departamento);
    }

    @Override
    public void eliminar(Long id) {
        if (!departamentoRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("Departamento no encontrado con ID para eliminar: " + id);
        }
        departamentoRepository.deleteById(id);
    }

    // Lógica específica para Reportes (CU-002)
    @Override
    @Transactional(readOnly = true)
    public BigDecimal obtenerSalarioPromedio(Long departamentoId) {
        // Opcional: Podrías buscar el departamento, pero el repositorio de empleados ya valida el ID
        // Aseguramos que se use la consulta optimizada del Repositorio de Empleados
        return empleadoRepository.findAverageSalarioByDepartamento(departamentoId)
                .orElse(BigDecimal.ZERO);
    }
}