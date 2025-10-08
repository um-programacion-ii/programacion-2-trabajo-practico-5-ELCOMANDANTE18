package com.um.prog2.gestion_empleados.controller;

import com.um.prog2.gestion_empleados.model.Proyecto;
import com.um.prog2.gestion_empleados.service.ProyectoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/proyectos")
public class ProyectoController {

    private final ProyectoService proyectoService;

    public ProyectoController(ProyectoService proyectoService) {
        this.proyectoService = proyectoService;
    }

    // CRUD Básico
    @GetMapping
    public List<Proyecto> obtenerTodos() {
        return proyectoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Proyecto obtenerPorId(@PathVariable Long id) {
        return proyectoService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Proyecto crear(@Valid @RequestBody Proyecto proyecto) {
        return proyectoService.guardar(proyecto);
    }

    @PutMapping("/{id}")
    public Proyecto actualizar(@PathVariable Long id, @Valid @RequestBody Proyecto proyecto) {
        return proyectoService.actualizar(id, proyecto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        proyectoService.eliminar(id);
    }

    // Lógica Específica (Asignación y Filtrado - CU-003)
    @PutMapping("/{proyectoId}/empleados/{empleadoId}")
    public Proyecto asignarEmpleado(
            @PathVariable Long proyectoId,
            @PathVariable Long empleadoId) {
        return proyectoService.asignarEmpleado(proyectoId, empleadoId);
    }

    @DeleteMapping("/{proyectoId}/empleados/{empleadoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void designateEmpleado(
            @PathVariable Long proyectoId,
            @PathVariable Long empleadoId) {
        proyectoService.desasignarEmpleado(proyectoId, empleadoId);
    }

    @GetMapping("/estado")
    public List<Proyecto> filtrarPorEstado(@RequestParam String estado) {
        // Ejemplo: GET /api/proyectos/estado?estado=ACTIVO
        return proyectoService.filtrarProyectosPorEstado(estado);
    }
    }