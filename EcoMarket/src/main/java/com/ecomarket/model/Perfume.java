package com.ecomarket.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(exclude = {"categoria", "clientesFavoritos", "ingredientes"})
public class Perfume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(length = 500)
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FamiliaOlfativa familiaOlfativa;

    @Column(nullable = false, precision = 10, scale = 2)
    private Double precio;

    @Column(nullable = false)
    private Integer stock;

    @Column(name = "imagen_url", length = 255)
    private String imagenUrl;

    @Column(nullable = false)
    private Boolean vegano = false;

    @Column(name = "cruelty_free", nullable = false)
    private Boolean crueltyFree = false;

    @Column(name = "intensidad", length = 20)
    private String intensidad; // Ligera, Moderada, Alta

    @ElementCollection
    @CollectionTable(name = "perfume_ingredientes", joinColumns = @JoinColumn(name = "perfume_id"))
    @Column(name = "ingrediente")
    private Set<String> ingredientes = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @ManyToMany(mappedBy = "perfumesFavoritos")
    private Set<Cliente> clientesFavoritos = new HashSet<>();

    public enum FamiliaOlfativa {
        FLORAL, ORIENTAL, AMADERADA, CITRICA, AROMATICA, FRUTAL, MARINA
    }

    // Métodos de negocio
    public void reducirStock(Integer cantidad) {
        if (this.stock >= cantidad) {
            this.stock -= cantidad;
        } else {
            throw new IllegalStateException("Stock insuficiente para el perfume: " + this.nombre);
        }
    }

    public void agregarIngrediente(String ingrediente) {
        this.ingredientes.add(ingrediente);
    }

    public boolean esAptoParaVeganos() {
        return this.vegano && this.crueltyFree;
    }
}