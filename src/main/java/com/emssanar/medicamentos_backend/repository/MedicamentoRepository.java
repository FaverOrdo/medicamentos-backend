package com.emssanar.medicamentos_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emssanar.medicamentos_backend.model.Medicamento;

/**
 * Repositorio JPA para la entidad Medicamento.
 * Proporciona métodos básicos para realizar operaciones CRUD.
 */
public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {

    
}
