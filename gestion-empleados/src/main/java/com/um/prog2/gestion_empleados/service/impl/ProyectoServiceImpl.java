package com.um.prog2.gestion_empleados.service.impl;

import com.um.prog2.gestion_empleados.model.Empleado;
import com.um.prog2.gestion_empleados.model.Proyecto;
import com.um.prog2.gestion_empleados.repository.EmpleadoRepository;
import com.um.prog2.gestion_empleados.repository.ProyectoRepository;
import com.um.prog2.gestion_empleados.service.ProyectoService;
import com.um.prog2.gestion_empleados.service.exception.RecursoNoEncontradoException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class ProyectoServiceImpl implements ProyectoService {

    private final ProyectoRepository proyectoRepository;
    private final EmpleadoRepository empleadoRepository;

    public ProyectoServiceImpl(ProyectoRepository proyectoRepository, EmpleadoRepository empleadoRepository) {
        this.proyectoRepository = proyectoRepository;
        this.empleadoRepository = empleadoRepository;
    }

    // Métodos CRUD
    @Override
    public Proyecto guardar(Proyecto proyecto) {
        return proyectoRepository.save(proyecto);
    }

    @Override
    @Transactional(readOnly = true)
    public Proyecto buscarPorId(Long id) {
        return proyectoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Proyecto no encontrado con ID: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Proyecto> obtenerTodos() {
        return proyectoRepository.findAll();
    }

    @Override
    public Proyecto actualizar(Long id, Proyecto proyecto) {
        if (!proyectoRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("Proyecto no encontrado con ID para actualizar: " + id);
        }
        proyecto.setId(id);
        return proyectoRepository.save(proyecto);
    }

    @Override
    public void eliminar(Long id) {
        if (!proyectoRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("Proyecto no encontrado con ID para eliminar: " + id);
        }
        proyectoRepository.deleteById(id);
    }

    // Lógica Específica (Asignación y Filtrado)

    @Override
    public Proyecto asignarEmpleado(Long proyectoId, Long empleadoId) {
        Proyecto proyecto = buscarPorId(proyectoId);
        Empleado empleado = empleadoRepository.findById(empleadoId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Empleado no encontrado con ID: " + empleadoId));

        proyecto.getEmpleados().add(empleado);
        empleado.getProyectos().add(proyecto);

        return proyectoRepository.save(proyecto);
    }

    @Override
    public void desasignarEmpleado(Long proyectoId, Long empleadoId) {
        Proyecto proyecto = buscarPorId(proyectoId);
        Empleado empleado = empleadoRepository.findById(empleadoId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Empleado no encontrado con ID: " + empleadoId));

        proyecto.getEmpleados().remove(empleado);
        empleado.getProyectos().remove(proyecto);

        proyectoRepository.save(proyecto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Proyecto> filtrarProyectosPorEstado(String estado) {
        LocalDate hoy = LocalDate.now();
        if ("ACTIVO".equalsIgnoreCase(estado)) {
            // Asume que el Repositorio tiene un método findByFechaFinIsNullOrFechaFinAfter(LocalDate fecha)
            return proyectoRepository.findByFechaFinIsNullOrFechaFinAfter(hoy);
        } else if ("INACTIVO".equalsIgnoreCase(estado)) {
            // Proyectos con fecha fin pasada
            return proyectoRepository.findByFechaFinBefore(hoy);
        }
        return obtenerTodos();
    }
}

