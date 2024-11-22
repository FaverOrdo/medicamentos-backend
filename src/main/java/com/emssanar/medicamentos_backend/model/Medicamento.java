package com.emssanar.medicamentos_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;


/**
 * Clase que representa un Medicamento en el sistema.
 */
@Entity // Marca esta clase como una entidad JPA
public class Medicamento {
    
    // ATRIBUTOS
    /**
     * ID único del medicamento (clave primaria).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre del medicamento.
     */
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    /**
     * Breve descripción del medicamento.
     */
    @NotBlank(message = "La descripción no puede estar vacía")
    private String descripcion;

    /**
     * Precio unitario del medicamento.
     */
    @Min(value = 0, message = "El precio debe ser un número positivo")
    private double precio;

    /**
     * Cantidad disponible en stock.
     */
    @Min(value = 0, message = "La cantidad debe ser un número positivo")
    private int cantidad;

    // CONSTRUCTORES

    /**
     * Constructor vacío necesario para JPA.
     */
    public Medicamento() {
        
    }

    /**
     * Constructor que permite inicializar todos los atributos del medicamento.
     *
     * @param nombre Nombre del medicamento.
     * @param descripcion Descripción del medicamento.
     * @param precio Precio del medicamento.
     * @param cantidad Cantidad en stock.
     */
    public Medicamento(String nombre, String descripcion, double precio, int cantidad) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    // MÉTODOS GETTERS Y SETTERS

    /**
     * Obtiene el ID del medicamento.
     *
     * @return ID del medicamento.
     */
    public Long getId() {
        return id;
    }

    /**
     * Asigna el ID del medicamento.
     *
     * @param id ID único del medicamento.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del medicamento.
     *
     * @return Nombre del medicamento.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Asigna el nombre del medicamento.
     *
     * @param nombre Nombre del medicamento.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la descripción del medicamento.
     *
     * @return Descripción del medicamento.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Asigna la descripción del medicamento.
     *
     * @param descripcion Descripción del medicamento.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el precio del medicamento.
     *
     * @return Precio del medicamento.
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Asigna el precio del medicamento.
     *
     * @param precio Precio del medicamento.
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Obtiene la cantidad disponible del medicamento.
     *
     * @return Cantidad disponible.
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Asigna la cantidad disponible del medicamento.
     *
     * @param cantidad Cantidad disponible.
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
