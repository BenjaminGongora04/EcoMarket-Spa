package com.ecomarket.dto;

import java.math.BigDecimal;
import java.util.List;

public class PerfumeDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private Integer stock;
    private Long categoriaId;
    private String categoriaNombre;
    private List<String> ecoCertificaciones;
    private List<String> imagenes;
    private boolean activo;

    // Constructores
    public PerfumeDTO() {
    }

    public PerfumeDTO(Long id, String nombre, String descripcion, BigDecimal precio,
                      Integer stock, Long categoriaId, String categoriaNombre) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.categoriaId = categoriaId;
        this.categoriaNombre = categoriaNombre;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getCategoriaNombre() {
        return categoriaNombre;
    }

    public void setCategoriaNombre(String categoriaNombre) {
        this.categoriaNombre = categoriaNombre;
    }

    public List<String> getEcoCertificaciones() {
        return ecoCertificaciones;
    }

    public void setEcoCertificaciones(List<String> ecoCertificaciones) {
        this.ecoCertificaciones = ecoCertificaciones;
    }

    public List<String> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<String> imagenes) {
        this.imagenes = imagenes;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    // Métodos utilitarios
    @Override
    public String toString() {
        return "PerfumeDTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", categoriaId=" + categoriaId +
                ", precio=" + precio +
                ", stock=" + stock +
                '}';
    }
}