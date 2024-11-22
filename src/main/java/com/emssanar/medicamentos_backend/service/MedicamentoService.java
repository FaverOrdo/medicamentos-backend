package com.emssanar.medicamentos_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emssanar.medicamentos_backend.model.Medicamento;
import com.emssanar.medicamentos_backend.repository.MedicamentoRepository;

/**
 * Servicio que contiene la lógica de negocio para la entidad Medicamento.
 */
@Service
public class MedicamentoService {

    // ATRIBUTOS
    @Autowired
    private MedicamentoRepository medicamentoRepository;

    // MÉTODOS

    /**
     * Obtiene la lista de todos los medicamentos.
     *
     * @return Lista de medicamentos.
     */
    public List<Medicamento> obtenerTodos() {
        return medicamentoRepository.findAll();
    }

    /**
     * Busca un medicamento por su ID.
     *
     * @param id ID del medicamento.
     * @return Medicamento encontrado o vacío si no existe.
     */
    public Optional<Medicamento> obtenerPorId(Long id) {
        return medicamentoRepository.findById(id);
    }

    /**
     * Crea un nuevo medicamento.
     *
     * @param medicamento Objeto medicamento con los datos a guardar.
     * @return Medicamento creado.
     */
    public Medicamento guardarMedicamento(Medicamento medicamento) {
        if (medicamento.getId() != null && medicamento.getId() > 0) {
            // Si el ID no es nulo y mayor a 0, actualiza el registro
            return medicamentoRepository.save(medicamento);
        } else {
            // Si el ID es nulo o 0, crea un nuevo registro
            return medicamentoRepository.save(medicamento);
        }
    }    

    /**
     * Actualiza un medicamento existente.
     *
     * @param id ID del medicamento a actualizar.
     * @param detalles Datos nuevos del medicamento.
     * @return Medicamento actualizado o vacío si no existe.
     */
    public Optional<Medicamento> actualizar(Long id, Medicamento detalles) {
        return medicamentoRepository.findById(id).map(medicamento -> {
            medicamento.setNombre(detalles.getNombre());
            medicamento.setDescripcion(detalles.getDescripcion());
            medicamento.setPrecio(detalles.getPrecio());
            medicamento.setCantidad(detalles.getCantidad());
            return medicamentoRepository.save(medicamento);
        });
    }

    /**
     * Elimina un medicamento por su ID.
     *
     * @param id ID del medicamento a eliminar.
     * @return True si fue eliminado, False si no se encontró.
     */
    public boolean eliminar(Long id) {
        return medicamentoRepository.findById(id).map(medicamento -> {
            medicamentoRepository.delete(medicamento);
            return true;
        }).orElse(false);
    }
}
