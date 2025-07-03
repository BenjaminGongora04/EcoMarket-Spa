package com.ecomarket.repository;

import com.ecomarket.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    // Buscar categoría por nombre exacto (case insensitive)
    Optional<Categoria> findByNombreIgnoreCase(String nombre);

    // Buscar categorías que contengan en el nombre (case insensitive)
    List<Categoria> findByNombreContainingIgnoreCase(String nombre);

    // Buscar categorías activas
    List<Categoria> findByActivoTrue();

    // Buscar categorías por IDs
    @Query("SELECT c FROM Categoria c WHERE c.id IN :ids")
    List<Categoria> findByIds(@Param("ids") List<Long> ids);

    // Contar productos asociados a una categoría
    @Query("SELECT COUNT(p) FROM Perfume p WHERE p.categoria.id = :categoriaId")
    Long countProductosByCategoriaId(@Param("categoriaId") Long categoriaId);

    // Buscar categorías con productos en stock
    @Query("SELECT DISTINCT p.categoria FROM Perfume p WHERE p.stock > 0")
    List<Categoria> findCategoriasConProductosEnStock();

    // Buscar categorías por tipo (ejemplo de extensión)
    @Query("SELECT c FROM Categoria c WHERE c.tipo = :tipo")
    List<Categoria> findByTipo(@Param("tipo") String tipo);
}