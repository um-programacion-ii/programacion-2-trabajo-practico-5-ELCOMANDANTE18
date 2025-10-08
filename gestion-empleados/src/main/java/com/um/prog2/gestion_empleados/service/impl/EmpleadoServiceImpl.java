package com.um.prog2.gestion_empleados.service.impl;

import com.um.prog2.gestion_empleados.model.Empleado;
import com.um.prog2.gestion_empleados.repository.EmpleadoRepository;
import com.um.prog2.gestion_empleados.service.EmpleadoService;
import com.um.prog2.gestion_empleados.service.exception.EmailDuplicadoException;
import com.um.prog2.gestion_empleados.service.exception.RecursoNoEncontradoException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class EmpleadoServiceImpl implements EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    public EmpleadoServiceImpl(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    // Métodos CRUD y Lógica

    @Override
    public Empleado guardar(Empleado empleado) {
        // Validación de unicidad de email (Lógica de Negocio Requerida)
        if (empleadoRepository.findByEmail(empleado.getEmail()).isPresent()) {
            throw new EmailDuplicadoException("El email ya está registrado: " + empleado.getEmail());
        }
        return empleadoRepository.save(empleado);
    }

    @Override
    @Transactional(readOnly = true)
    public Empleado buscarPorId(Long id) {
        return empleadoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Empleado no encontrado con ID: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Empleado> obtenerTodos() {
        return empleadoRepository.findAll();
    }

    @Override
    public Empleado actualizar(Long id, Empleado empleado) {
        if (!empleadoRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("Empleado no encontrado con ID para actualizar: " + id);
        }
        empleado.setId(id);
        // Opcional: Podrías añadir validación de email duplicado si el email ha cambiado
        return empleadoRepository.save(empleado);
    }

    @Override
    public void eliminar(Long id) {
        if (!empleadoRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("Empleado no encontrado con ID para eliminar: " + id);
        }
        empleadoRepository.deleteById(id);
    }

    // Consultas Específicas (Reportes)

    @Override
    @Transactional(readOnly = true)
    public List<Empleado> buscarPorDepartamento(String nombreDepartamento) {
        // Usa la consulta JPQL personalizada del Repositorio
        return empleadoRepository.findByNombreDepartamento(nombreDepartamento);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Empleado> buscarPorRangoSalario(BigDecimal salarioMin, BigDecimal salarioMax) {
        return empleadoRepository.findBySalarioBetween(salarioMin, salarioMax);
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal obtenerSalarioPromedioPorDepartamento(Long departamentoId) {
        return empleadoRepository.findAverageSalarioByDepartamento(departamentoId)
                .orElse(BigDecimal.ZERO);
    }
}

