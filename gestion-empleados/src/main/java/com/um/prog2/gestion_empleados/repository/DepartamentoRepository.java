package com.um.prog2.gestion_empleados.repository;

import com.um.prog2.gestion_empleados.model.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
    // Spring Data JPA ya proporciona métodos CRUD y findById
}
