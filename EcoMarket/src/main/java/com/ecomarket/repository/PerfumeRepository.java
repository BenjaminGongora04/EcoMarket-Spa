package com.ecomarket.repository;

import com.ecomarket.model.Perfume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerfumeRepository extends JpaRepository<Perfume, Long> {

    // Método para buscar perfumes por nombre (contiene)
    List<Perfume> findByNombreContainingIgnoreCase(String nombre);

    // Método para buscar perfumes por categoría
    @Query("SELECT p FROM Perfume p WHERE p.categoria.id = :categoriaId")
    List<Perfume> findByCategoriaId(@Param("categoriaId") Long categoriaId);

    // Método para buscar perfumes en stock (stock > 0)
    List<Perfume> findByStockGreaterThan(Integer stock);

    // Método para buscar perfumes por rango de precio
    @Query("SELECT p FROM Perfume p WHERE p.precio BETWEEN :precioMin AND :precioMax")
    List<Perfume> findByPrecioBetween(@Param("precioMin") Double precioMin,
                                      @Param("precioMax") Double precioMax);

    // Método para buscar perfumes por certificación ecológica
    @Query("SELECT p FROM Perfume p WHERE :certificacion MEMBER OF p.ecoCertificaciones")
    List<Perfume> findByEcoCertificacion(@Param("certificacion") String certificacion);
}