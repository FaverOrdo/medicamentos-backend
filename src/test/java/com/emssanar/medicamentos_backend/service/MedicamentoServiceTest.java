package com.emssanar.medicamentos_backend.service;

import com.emssanar.medicamentos_backend.model.Medicamento;
import com.emssanar.medicamentos_backend.repository.MedicamentoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MedicamentoServiceTest {

    @Mock
    private MedicamentoRepository medicamentoRepository;

    @InjectMocks
    private MedicamentoService medicamentoService;

    @Test
    void obtenerTodosTest() {
        // Arrange
        List<Medicamento> medicamentos = Arrays.asList(
                new Medicamento( "Paracetamol", "Alivia el dolor", 5000.0, 100),
                new Medicamento( "Ibuprofeno", "Antiinflamatorio", 8000.0, 50)
        );
        when(medicamentoRepository.findAll()).thenReturn(medicamentos);

        // Act
        List<Medicamento> resultado = medicamentoService.obtenerTodos();

        // Assert
        assertEquals(2, resultado.size());
        assertEquals(medicamentos, resultado);
        verify(medicamentoRepository, times(1)).findAll();
    }

    @Test
    void obtenerPorIdTest() {
        // Arrange
        Long id = 1L;
        Medicamento medicamento = new Medicamento( "Paracetamol", "Alivia el dolor", 5000.0, 100);
        when(medicamentoRepository.findById(id)).thenReturn(Optional.of(medicamento));

        // Act
        Optional<Medicamento> resultado = medicamentoService.obtenerPorId(id);

        // Assert
        assertTrue(resultado.isPresent());
        assertEquals(medicamento, resultado.get());
        verify(medicamentoRepository, times(1)).findById(id);
    }

    @Test
    void guardarMedicamentoTest() {
        // Arrange
        Medicamento medicamento = new Medicamento( "Paracetamol", "Alivia el dolor", 5000.0, 100);
        Medicamento medicamentoGuardado = new Medicamento( "Paracetamol", "Alivia el dolor", 5000.0, 100);
        when(medicamentoRepository.save(medicamento)).thenReturn(medicamentoGuardado);

        // Act
        Medicamento resultado = medicamentoService.guardarMedicamento(medicamento);

        // Assert
        assertNotNull(resultado);
        assertEquals(medicamentoGuardado, resultado);
        verify(medicamentoRepository, times(1)).save(medicamento);
    }

    @Test
    void actualizarTest() {
        // Arrange
        Long id = 1L;
        Medicamento medicamentoExistente = new Medicamento( "Ibuprofeno", "Antiinflamatorio", 8000.0, 50);
        Medicamento detallesActualizados = new Medicamento( "Paracetamol", "Alivia el dolor", 5000.0, 100);
        Medicamento medicamentoActualizado = new Medicamento( "Paracetamol", "Alivia el dolor", 5000.0, 100);

        when(medicamentoRepository.findById(id)).thenReturn(Optional.of(medicamentoExistente));
        when(medicamentoRepository.save(any(Medicamento.class))).thenReturn(medicamentoActualizado);

        // Act
        Optional<Medicamento> resultado = medicamentoService.actualizar(id, detallesActualizados);

        // Assert
        assertTrue(resultado.isPresent());
        assertEquals(medicamentoActualizado.getNombre(), resultado.get().getNombre());
        verify(medicamentoRepository, times(1)).findById(id);
        verify(medicamentoRepository, times(1)).save(any(Medicamento.class));
    }

    @Test
    void eliminarTest() {
        // Arrange
        Long id = 1L;
        Medicamento medicamento = new Medicamento( "Paracetamol", "Alivia el dolor", 5000.0, 100);
        when(medicamentoRepository.findById(id)).thenReturn(Optional.of(medicamento));

        // Act
        boolean resultado = medicamentoService.eliminar(id);

        // Assert
        assertTrue(resultado);
        verify(medicamentoRepository, times(1)).findById(id);
        verify(medicamentoRepository, times(1)).delete(medicamento);
    }

    @Test
    void eliminarMedicamentoNoExistenteTest() {
        // Arrange
        Long id = 999L;
        when(medicamentoRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        boolean resultado = medicamentoService.eliminar(id);

        // Assert
        assertFalse(resultado);
        verify(medicamentoRepository, times(1)).findById(id);
        verify(medicamentoRepository, never()).delete(any(Medicamento.class));
    }
}
