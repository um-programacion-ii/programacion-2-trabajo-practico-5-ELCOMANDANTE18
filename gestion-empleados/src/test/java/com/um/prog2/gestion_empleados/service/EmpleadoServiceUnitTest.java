package com.um.prog2.gestion_empleados.service;

import com.um.prog2.gestion_empleados.model.Empleado;
import com.um.prog2.gestion_empleados.repository.EmpleadoRepository;
import com.um.prog2.gestion_empleados.service.exception.EmailDuplicadoException;
import com.um.prog2.gestion_empleados.service.exception.RecursoNoEncontradoException;
import com.um.prog2.gestion_empleados.service.impl.EmpleadoServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmpleadoServiceUnitTest {

    @Mock
    private EmpleadoRepository empleadoRepository;

    @InjectMocks
    private EmpleadoServiceImpl empleadoService;

    // Helper para crear un empleado de prueba
    private Empleado crearEmpleadoDePrueba(String email) {
        Empleado e = new Empleado();
        e.setNombre("Test");
        e.setApellido("User");
        e.setEmail(email);
        e.setSalario(new BigDecimal("50000.00"));
        e.setFechaContratacion(LocalDate.now());
        return e;
    }

    // Test 1: Verificar que se guarda correctamente
    @Test
    void cuandoGuardarEmpleado_entoncesSeRetornaEmpleado() {
        Empleado nuevoEmpleado = crearEmpleadoDePrueba("nuevo@empresa.com");

        // Simular que el email NO existe
        when(empleadoRepository.findByEmail("nuevo@empresa.com")).thenReturn(Optional.empty());
        // Simular que el repositorio lo guarda
        when(empleadoRepository.save(any(Empleado.class))).thenReturn(nuevoEmpleado);

        Empleado resultado = empleadoService.guardar(nuevoEmpleado);

        assertNotNull(resultado);
        // Verifica que el método save() del repositorio fue llamado 1 vez
        verify(empleadoRepository, times(1)).save(nuevoEmpleado);
    }

    // Test 2: Validar Email Duplicado (Lógica de Negocio)
    @Test
    void cuandoGuardarEmpleadoConEmailExistente_entoncesLanzaExcepcion() {
        String emailExistente = "duplicado@empresa.com";
        Empleado empleadoDuplicado = crearEmpleadoDePrueba(emailExistente);

        // Simular que el email YA existe
        when(empleadoRepository.findByEmail(emailExistente)).thenReturn(Optional.of(empleadoDuplicado));

        // Verificar que se lanza la excepción correcta
        assertThrows(EmailDuplicadoException.class, () -> {
            empleadoService.guardar(empleadoDuplicado);
        });

        // Verificar que el método save() NO fue llamado
        verify(empleadoRepository, never()).save(any(Empleado.class));
    }

    // Test 3: Validar Empleado No Encontrado por ID
    @Test
    void cuandoBuscarPorIdNoExistente_entoncesLanzaExcepcion() {
        Long idInexistente = 99L;

        // Simular que el repositorio retorna vacío
        when(empleadoRepository.findById(idInexistente)).thenReturn(Optional.empty());

        // Verificar que se lanza la excepción correcta (RecursoNoEncontradoException)
        assertThrows(RecursoNoEncontradoException.class, () -> {
            empleadoService.buscarPorId(idInexistente);
        });
    }
}