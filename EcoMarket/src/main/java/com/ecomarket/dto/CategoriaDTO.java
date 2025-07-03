package com.ecomarket.dto;

import java.util.List;

public class CategoriaDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private boolean activo;
    private Integer cantidadProductos;
    private List<String> etiquetas;
    private String imagenUrl;

    // Constructores
    public CategoriaDTO() {
    }

    public CategoriaDTO(Long id, String nombre, String descripcion, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.activo = activo;
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

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Integer getCantidadProductos() {
        return cantidadProductos;
    }

    public void setCantidadProductos(Integer cantidadProductos) {
        this.cantidadProductos = cantidadProductos;
    }

    public List<String> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(List<String> etiquetas) {
        this.etiquetas = etiquetas;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    // Métodos utilitarios
    @Override
    public String toString() {
        return "CategoriaDTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", activo=" + activo +
                ", cantidadProductos=" + cantidadProductos +
                '}';
    }

    // Patrón Builder (opcional)
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String nombre;
        private String descripcion;
        private boolean activo;
        private Integer cantidadProductos;
        private List<String> etiquetas;
        private String imagenUrl;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder nombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder descripcion(String descripcion) {
            this.descripcion = descripcion;
            return this;
        }

        public Builder activo(boolean activo) {
            this.activo = activo;
            return this;
        }

        public Builder cantidadProductos(Integer cantidadProductos) {
            this.cantidadProductos = cantidadProductos;
            return this;
        }

        public Builder etiquetas(List<String> etiquetas) {
            this.etiquetas = etiquetas;
            return this;
        }

        public Builder imagenUrl(String imagenUrl) {
            this.imagenUrl = imagenUrl;
            return this;
        }

        public CategoriaDTO build() {
            CategoriaDTO dto = new CategoriaDTO();
            dto.setId(this.id);
            dto.setNombre(this.nombre);
            dto.setDescripcion(this.descripcion);
            dto.setActivo(this.activo);
            dto.setCantidadProductos(this.cantidadProductos);
            dto.setEtiquetas(this.etiquetas);
            dto.setImagenUrl(this.imagenUrl);
            return dto;
        }
    }
}