package com.ecomarket.controller;

import com.ecomarket.dto.PerfumeDTO;
import com.ecomarket.model.Perfume;
import com.ecomarket.service.PerfumeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/perfumes")
public class PerfumeController {

    private final PerfumeService perfumeService;

    public PerfumeController(PerfumeService perfumeService) {
        this.perfumeService = perfumeService;
    }

    @GetMapping
    public ResponseEntity<Page<PerfumeDTO>> getAllPerfumes(Pageable pageable) {
        return ResponseEntity.ok(perfumeService.findAllPerfumes(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerfumeDTO> getPerfumeById(@PathVariable Long id) {
        return ResponseEntity.ok(perfumeService.findPerfumeById(id));
    }

    @GetMapping("/familia/{familia}")
    public ResponseEntity<List<PerfumeDTO>> getPerfumesByFamily(
            @PathVariable String familia) {
        return ResponseEntity.ok(perfumeService.findByFamiliaOlfativa(familia));
    }

    @GetMapping("/veganos")
    public ResponseEntity<List<PerfumeDTO>> getVeganPerfumes() {
        return ResponseEntity.ok(perfumeService.findVeganPerfumes());
    }

    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<PerfumeDTO>> getPerfumesByCategory(
            @PathVariable Long categoriaId) {
        return ResponseEntity.ok(perfumeService.findByCategory(categoriaId));
    }

    @GetMapping("/search")
    public ResponseEntity<List<PerfumeDTO>> searchPerfumes(
            @RequestParam String query) {
        return ResponseEntity.ok(perfumeService.searchPerfumes(query));
    }

    @PostMapping
    public ResponseEntity<PerfumeDTO> createPerfume(
            @RequestBody PerfumeDTO perfumeDTO) {
        return ResponseEntity.ok(perfumeService.createPerfume(perfumeDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PerfumeDTO> updatePerfume(
            @PathVariable Long id,
            @RequestBody PerfumeDTO perfumeDTO) {
        return ResponseEntity.ok(perfumeService.updatePerfume(id, perfumeDTO));
    }

    @PatchMapping("/{id}/stock")
    public ResponseEntity<Void> updateStock(
            @PathVariable Long id,
            @RequestParam Integer cantidad) {
        perfumeService.updateStock(id, cantidad);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerfume(@PathVariable Long id) {
        perfumeService.deletePerfume(id);
        return ResponseEntity.noContent().build();
    }
}