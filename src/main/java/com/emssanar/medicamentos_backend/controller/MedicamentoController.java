package com.emssanar.medicamentos_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emssanar.medicamentos_backend.model.Medicamento;
import com.emssanar.medicamentos_backend.repository.MedicamentoRepository;
import com.emssanar.medicamentos_backend.service.MedicamentoService;

import jakarta.persistence.Version;

@RestController
@RequestMapping("/api/medicamentos")
@CrossOrigin(origins = "http://localhost:4200")
public class MedicamentoController {
    
    // ATRIBUTOS
    /**
     * Repositorio para interactuar con la base de datos de medicamentos.
     */
    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @Autowired
    private MedicamentoService medicamentoService;

    @Version
    private Long version;


    // MÉTODOS

    /**
     * Obtiene la lista de todos los medicamentos almacenados.
     *
     * @return Lista de medicamentos.
     */
    @GetMapping
    public List<Medicamento> obtenerTodos() {
        return medicamentoRepository.findAll();
    }

    /**
     * Obtiene un medicamento específico basado en su ID.
     *
     * @param id ID del medicamento a buscar.
     * @return Medicamento encontrado o código 404 si no existe.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Medicamento> obtenerPorId(@PathVariable Long id) {
        return medicamentoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Crea un nuevo medicamento en la base de datos.
     *
     * @param medicamento Objeto medicamento con los datos a guardar.
     * @return Medicamento creado.
     */
    @PostMapping
    public ResponseEntity<?> crearMedicamento(@RequestBody Medicamento medicamento) {
    try {
        Medicamento nuevoMedicamento = medicamentoService.guardarMedicamento(medicamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoMedicamento);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar el medicamento: " + e.getMessage());
    }
    }

    /**
     * Actualiza un medicamento existente basado en su ID.
     *
     * @param id ID del medicamento a actualizar.
     * @param detalles Objeto medicamento con los datos nuevos.
     * @return Medicamento actualizado o código 404 si no existe.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Medicamento> actualizar(@PathVariable Long id, @RequestBody Medicamento detalles) {
        return medicamentoRepository.findById(id)
                .map(medicamento -> {
                    medicamento.setNombre(detalles.getNombre());
                    medicamento.setDescripcion(detalles.getDescripcion());
                    medicamento.setPrecio(detalles.getPrecio());
                    medicamento.setCantidad(detalles.getCantidad());
                    return ResponseEntity.ok(medicamentoRepository.save(medicamento));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Elimina un medicamento de la base de datos.
     *
     * Este método busca un medicamento por su ID. Si el medicamento existe, lo elimina
     * de la base de datos y devuelve una respuesta HTTP con código 204 (No Content).
     * Si el medicamento no se encuentra, devuelve una respuesta HTTP con código 404 (Not Found).
     *
     * @param id ID del medicamento que se desea eliminar.
     * @return Respuesta HTTP:
     *         - 204 No Content: Si el medicamento fue eliminado correctamente.
     *         - 404 Not Found: Si el medicamento no existe en la base de datos.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        var medicamentoOptional = medicamentoRepository.findById(id);
        if (medicamentoOptional.isPresent()) {
            medicamentoRepository.delete(medicamentoOptional.get());
            // Retornar código HTTP 204 No Content
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }



   
}
