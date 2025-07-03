package com.ecomarket.controller;

import com.ecomarket.dto.CategoriaDTO;
import com.ecomarket.dto.PerfumeDTO;
import java.util.List;
import org.springframework.http.ResponseEntity;
import com.ecomarket.model.Categoria;
import com.ecomarket.service.CategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> getAllCategories() {
        return ResponseEntity.ok(categoriaService.findAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.findCategoryById(id));
    }

    @GetMapping("/{id}/perfumes")
    public ResponseEntity<List<PerfumeDTO>> getPerfumesByCategory(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.findPerfumesByCategory(id));
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> createCategory(@RequestBody CategoriaDTO categoriaDTO) {
        return ResponseEntity.ok(categoriaService.createCategory(categoriaDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> updateCategory(
            @PathVariable Long id,
            @RequestBody CategoriaDTO categoriaDTO) {
        return ResponseEntity.ok(categoriaService.updateCategory(id, categoriaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoriaService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/top")
    public ResponseEntity<List<CategoriaDTO>> getTopCategories(
            @RequestParam(defaultValue = "5") int count) {
        return ResponseEntity.ok(categoriaService.findTopCategories(count));
    }
}