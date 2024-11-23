package com.emssanar.medicamentos_backend.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;



import com.emssanar.medicamentos_backend.model.Medicamento;
import com.emssanar.medicamentos_backend.service.MedicamentoService;

public class MedicamentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MedicamentoService medicamentoService;

    @Test
    void obtenerTodosMedicamentosTest() throws Exception {
        // Arrange
        Medicamento medicamento1 = new Medicamento( "Paracetamol", "Alivia el dolor", 3000.0, 200);
        Medicamento medicamento2 = new Medicamento( "Ibuprofeno", "Antiinflamatorio", 5000.0, 100);
        when(medicamentoService.obtenerTodos()).thenReturn(Arrays.asList(medicamento1, medicamento2));

        // Act & Assert
        mockMvc.perform(get("/api/medicamentos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].nombre").value("Paracetamol"))
                .andExpect(jsonPath("$[1].nombre").value("Ibuprofeno"));

        verify(medicamentoService, times(1)).obtenerTodos();
    }

    @Test
    void obtenerMedicamentoPorIdTest() throws Exception {
        // Arrange
        Medicamento medicamento = new Medicamento( "Paracetamol", "Alivia el dolor", 3000.0, 200);
        when(medicamentoService.obtenerPorId(1L)).thenReturn(Optional.of(medicamento));

        // Act & Assert
        mockMvc.perform(get("/api/medicamentos/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Paracetamol"))
                .andExpect(jsonPath("$.descripcion").value("Alivia el dolor"));

        verify(medicamentoService, times(1)).obtenerPorId(1L);
    }

    @Test
    void guardarMedicamentoTest() throws Exception {
        // Arrange
        Medicamento medicamento = new Medicamento("Paracetamol", "Alivia el dolor", 3000.0, 200);
        Medicamento medicamentoGuardado = new Medicamento( "Paracetamol", "Alivia el dolor", 3000.0, 200);
        when(medicamentoService.guardarMedicamento(any(Medicamento.class))).thenReturn(medicamentoGuardado);

        // Act & Assert
        mockMvc.perform(post("/api/medicamentos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "nombre": "Paracetamol",
                            "descripcion": "Alivia el dolor",
                            "precio": 3000.0,
                            "cantidad": 200
                        }
                        """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Paracetamol"));

        verify(medicamentoService, times(1)).guardarMedicamento(any(Medicamento.class));
    }

    @Test
    void eliminarMedicamentoTest() throws Exception {
        // Arrange
        when(medicamentoService.eliminar(1L)).thenReturn(true);

        // Act & Assert
        mockMvc.perform(delete("/api/medicamentos/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(medicamentoService, times(1)).eliminar(1L);
    }


}
