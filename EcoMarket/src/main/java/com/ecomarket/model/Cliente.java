package com.ecomarket.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "clientes")
@EqualsAndHashCode(exclude = {"pedidos", "perfumesFavoritos"})
@ToString(exclude = {"pedidos", "perfumesFavoritos"})
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String apellido;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(length = 20)
    private String telefono;

    @Embedded
    private Direccion direccion;

    @Column(name = "puntos_eco", columnDefinition = "integer default 0")
    private Integer puntosEco = 0;

    @Column(name = "es_verificado", columnDefinition = "boolean default false")
    private Boolean esVerificado = false;

    @ElementCollection
    @CollectionTable(name = "cliente_preferencias", joinColumns = @JoinColumn(name = "cliente_id"))
    private Set<String> familiasOlfativasPreferidas = new HashSet<>();

    @Column(name = "prefiere_veganos", columnDefinition = "boolean default false")
    private Boolean prefiereVeganos = false;

    @Column(name = "prefiere_cruelty_free", columnDefinition = "boolean default falsASe")
    private Boolean prefiereCrueltyFree = false;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pedido> pedidos = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "cliente_perfume_favoritos",
            joinColumns = @JoinColumn(name = "cliente_id"),
            inverseJoinColumns = @JoinColumn(name = "perfume_id")
    )
    private Set<Perfume> perfumesFavoritos = new HashSet<>();

    @Embeddable
    @Data
    public static class Direccion {
        private String calle;
        private String ciudad;
        private String codigoPostal;
        private String pais;
    }

    // Métodos de negocio
    public void agregarPedido(Pedido pedido) {
        pedidos.add(pedido);
        pedido.setCliente(this);
    }

    public void agregarFavorito(Perfume perfume) {
        perfumesFavoritos.add(perfume);
        perfume.getClientesFavoritos().add(this);
    }

    public void removerFavorito(Perfume perfume) {
        perfumesFavoritos.remove(perfume);
        perfume.getClientesFavoritos().remove(this);
    }

    public void acumularPuntosEco(Integer puntos) {
        this.puntosEco += puntos;
    }

    public boolean tienePreferenciasEco() {
        return prefiereVeganos || prefiereCrueltyFree || !familiasOlfativasPreferidas.isEmpty();
    }
}