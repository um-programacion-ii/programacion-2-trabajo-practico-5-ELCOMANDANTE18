package com.um.prog2.gestion_empleados.repository;

import com.um.prog2.gestion_empleados.model.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {

    // Método derivado para el filtrado por estado "ACTIVO":
    // Proyectos cuya fecha de fin es nula (aún no tienen fecha de fin) o es posterior a la fecha actual.
    // Esto es fundamental para el Caso de Uso CU-003.
    List<Proyecto> findByFechaFinIsNullOrFechaFinAfter(LocalDate fecha);

    // Método derivado para el filtrado por estado "INACTIVO":
    // Proyectos cuya fecha de fin es anterior a la fecha actual.
    List<Proyecto> findByFechaFinBefore(LocalDate fecha);

    // Opcional: Buscar proyecto por nombre
    Optional<Proyecto> findByNombre(String nombre);
}