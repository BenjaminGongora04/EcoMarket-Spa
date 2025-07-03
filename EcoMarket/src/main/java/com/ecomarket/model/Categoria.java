package com.ecomarket.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@ToString(exclude = "perfumes")
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String nombre;

    @Column(length = 200)
    private String descripcion;

    @Column(name = "imagen_url", length = 255)
    private String imagenUrl;

    @Column(name = "icono", length = 30)
    private String icono;

    @Column(name = "es_principal", nullable = false)
    private Boolean esPrincipal = false;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Perfume> perfumes = new ArrayList<>();

    // Métodos de negocio
    public void agregarPerfume(Perfume perfume) {
        perfumes.add(perfume);
        perfume.setCategoria(this);
    }

    public void removerPerfume(Perfume perfume) {
        perfumes.remove(perfume);
        perfume.setCategoria(null);
    }

    public int contarPerfumesVeganos() {
        return (int) perfumes.stream()
                .filter(Perfume::getVegano)
                .count();
    }

    public boolean esCategoriaPremium() {
        return contarPerfumesVeganos() >= 5;
    }
}