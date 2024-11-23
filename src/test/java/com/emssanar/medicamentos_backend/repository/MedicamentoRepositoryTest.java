package com.emssanar.medicamentos_backend.repository;

import com.emssanar.medicamentos_backend.model.Medicamento;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class MedicamentoRepositoryTest {

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @Test
    void guardarMedicamento_deberiaGuardarMedicamentoEnBaseDeDatos() {
        // Arrange
        Medicamento medicamento = new Medicamento();
        medicamento.setNombre("Paracetamol");
        medicamento.setDescripcion("Alivia el dolor y reduce la fiebre");
        medicamento.setPrecio(5000.0);
        medicamento.setCantidad(150);

        // Act
        Medicamento resultado = medicamentoRepository.save(medicamento);

        // Assert
        assertThat(resultado.getId()).isNotNull();
        assertThat(resultado.getNombre()).isEqualTo("Paracetamol");
        assertThat(resultado.getDescripcion()).isEqualTo("Alivia el dolor y reduce la fiebre");
        assertThat(resultado.getPrecio()).isEqualTo(5000.0);
        assertThat(resultado.getCantidad()).isEqualTo(150);
    }
}
