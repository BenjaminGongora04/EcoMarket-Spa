package com.ecomarket.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fecha;

    @Enumerated(EnumType.STRING)
    private EstadoPedido estado = EstadoPedido.PENDIENTE;

    private Double total = 0.0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> items = new ArrayList<>();

    // ENUM para estados
    public enum EstadoPedido {
        PENDIENTE, EN_PROCESO, ENVIADO, ENTREGADO, CANCELADO
    }

    // Constructor
    public Pedido() {
        this.fecha = LocalDateTime.now();
    }

    // Métodos de negocio
    public void agregarItem(ItemPedido item) {
        items.add(item);
        item.setPedido(this);
        calcularTotal();
    }

    public void calcularTotal() {
        this.total = items.stream()
                .mapToDouble(ItemPedido::getSubtotal)
                .sum();
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public Double getTotal() {
        return total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemPedido> getItems() {
        return items;
    }
}