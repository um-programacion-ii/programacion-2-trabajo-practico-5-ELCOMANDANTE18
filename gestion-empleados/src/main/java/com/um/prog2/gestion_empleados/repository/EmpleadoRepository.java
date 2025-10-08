package com.um.prog2.gestion_empleados.repository;

import com.um.prog2.gestion_empleados.model.Departamento;
import com.um.prog2.gestion_empleados.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    // 1. Buscar por email (usado para validación de unicidad)
    Optional<Empleado> findByEmail(String email);

    // 2. Buscar por objeto Departamento (útil en la capa de servicio)
    List<Empleado> findByDepartamento(Departamento departamento);

    // 3. Buscar por rango de salario (método derivado)
    List<Empleado> findBySalarioBetween(BigDecimal salarioMin, BigDecimal salarioMax);

    // Método derivado adicional de la consigna (CU-004)
    List<Empleado> findByFechaContratacionAfter(LocalDate fecha);

    // 4. Consulta JPQL: Empleados por nombre de departamento
    @Query("SELECT e FROM Empleado e WHERE e.departamento.nombre = :nombreDepartamento")
    List<Empleado> findByNombreDepartamento(@Param("nombreDepartamento") String nombreDepartamento);

    // 5. Consulta JPQL: Salario promedio por ID de departamento
    @Query("SELECT AVG(e.salario) FROM Empleado e WHERE e.departamento.id = :departamentoId")
    Optional<BigDecimal> findAverageSalarioByDepartamento(@Param("departamentoId") Long departamentoId);
}