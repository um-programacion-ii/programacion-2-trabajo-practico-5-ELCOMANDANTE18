package com.um.prog2.gestion_empleados.controller;

import com.um.prog2.gestion_empleados.model.Empleado;
import com.um.prog2.gestion_empleados.service.EmpleadoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/empleados")
@Validated // Permite usar validaciones en la clase (ej. en @RequestParam)
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    // 1. Obtener todos los empleados (GET /api/empleados)
    @GetMapping
    public List<Empleado> obtenerTodos() {
        return empleadoService.obtenerTodos();
    }

    // 2. Obtener por ID (GET /api/empleados/{id})
    @GetMapping("/{id}")
    public Empleado obtenerPorId(@PathVariable Long id) {
        // El service lanzará RecursoNoEncontradoException, manejada por el ControllerAdvice (Issue #15)
        return empleadoService.buscarPorId(id);
    }

    // 3. Crear nuevo empleado (POST /api/empleados)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Empleado crear(@Valid @RequestBody Empleado empleado) {
        // @Valid activa las validaciones de las anotaciones JPA/Bean Validation
        return empleadoService.guardar(empleado);
    }

    // 4. Actualizar empleado (PUT /api/empleados/{id})
    @PutMapping("/{id}")
    public Empleado actualizar(@PathVariable Long id, @Valid @RequestBody Empleado empleado) {
        return empleadoService.actualizar(id, empleado);
    }

    // 5. Eliminar empleado (DELETE /api/empleados/{id})
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204 No Content para eliminación exitosa
    public void eliminar(@PathVariable Long id) {
        empleadoService.eliminar(id);
    }

    // 6. Consultar por departamento (GET /api/empleados/departamento/{nombre}) - CU-004
    @GetMapping("/departamento/{nombre}")
    public List<Empleado> obtenerPorDepartamento(@PathVariable String nombre) {
        return empleadoService.buscarPorDepartamento(nombre);
    }

    // 7. Consultar por rango salarial (GET /api/empleados/salario?min=X&max=Y) - CU-004
    @GetMapping("/salario")
    public List<Empleado> obtenerPorRangoSalario(
            @RequestParam BigDecimal min,
            @RequestParam BigDecimal max) {
        return empleadoService.buscarPorRangoSalario(min, max);
    }
}
