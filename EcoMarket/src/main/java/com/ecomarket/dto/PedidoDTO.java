package com.ecomarket.dto;

import java.time.LocalDateTime;
import java.util.List;

public class PedidoDTO {
    private Long id;
    private LocalDateTime fecha;
    private Long clienteId;
    private String clienteNombre;
    private String estado;
    private Double total;
    private List<ItemPedidoDTO> items;

    // Constructores
    public PedidoDTO() {
    }

    public PedidoDTO(Long id, LocalDateTime fecha, Long clienteId, String estado) {
        this.id = id;
        this.fecha = fecha;
        this.clienteId = clienteId;
        this.estado = estado;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getClienteNombre() {
        return clienteNombre;
    }

    public void setClienteNombre(String clienteNombre) {
        this.clienteNombre = clienteNombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<ItemPedidoDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemPedidoDTO> items) {
        this.items = items;
    }
}