package com.ecomarket.service;

import com.ecomarket.dto.PerfumeDTO;
import com.ecomarket.exception.ResourceNotFoundException;
import com.ecomarket.model.Perfume;
import com.ecomarket.repository.PerfumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PerfumeService {

    private final PerfumeRepository perfumeRepository;

    @Autowired
    public PerfumeService(PerfumeRepository perfumeRepository) {
        this.perfumeRepository = perfumeRepository;
    }

    @Transactional(readOnly = true)
    public List<PerfumeDTO> findAllPerfumes() {
        return perfumeRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PerfumeDTO findPerfumeById(Long id) {
        Perfume perfume = perfumeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Perfume no encontrado con id: " + id));
        return convertToDTO(perfume);
    }

    @Transactional
    public PerfumeDTO createPerfume(PerfumeDTO perfumeDTO) {
        Perfume perfume = convertToEntity(perfumeDTO);
        Perfume savedPerfume = perfumeRepository.save(perfume);
        return convertToDTO(savedPerfume);
    }

    @Transactional
    public PerfumeDTO updatePerfume(Long id, PerfumeDTO perfumeDTO) {
        Perfume existingPerfume = perfumeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Perfume no encontrado con id: " + id));

        // Actualizar campos
        existingPerfume.setNombre(perfumeDTO.getNombre());
        existingPerfume.setDescripcion(perfumeDTO.getDescripcion());
        existingPerfume.setPrecio(perfumeDTO.getPrecio());
        existingPerfume.setStock(perfumeDTO.getStock());
        // Actualizar otros campos según necesidad

        Perfume updatedPerfume = perfumeRepository.save(existingPerfume);
        return convertToDTO(updatedPerfume);
    }

    @Transactional
    public void deletePerfume(Long id) {
        if (!perfumeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Perfume no encontrado con id: " + id);
        }
        perfumeRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<PerfumeDTO> searchPerfumes(String query) {
        return perfumeRepository.findByNombreContainingIgnoreCase(query)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PerfumeDTO> findPerfumesByCategoria(Long categoriaId) {
        return perfumeRepository.findByCategoriaId(categoriaId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public PerfumeDTO updateStock(Long id, Integer cantidad) {
        Perfume perfume = perfumeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Perfume no encontrado con id: " + id));

        perfume.setStock(perfume.getStock() + cantidad);
        Perfume updatedPerfume = perfumeRepository.save(perfume);
        return convertToDTO(updatedPerfume);
    }

    // Métodos de conversión DTO-Entity
    private PerfumeDTO convertToDTO(Perfume perfume) {
        PerfumeDTO dto = new PerfumeDTO();
        dto.setId(perfume.getId());
        dto.setNombre(perfume.getNombre());
        dto.setDescripcion(perfume.getDescripcion());
        dto.setPrecio(perfume.getPrecio());
        dto.setStock(perfume.getStock());
        // Convertir otros campos según necesidad
        return dto;
    }

    private Perfume convertToEntity(PerfumeDTO perfumeDTO) {
        Perfume perfume = new Perfume();
        perfume.setNombre(perfumeDTO.getNombre());
        perfume.setDescripcion(perfumeDTO.getDescripcion());
        perfume.setPrecio(perfumeDTO.getPrecio());
        perfume.setStock(perfumeDTO.getStock());
        // Establecer otros campos según necesidad
        return perfume;
    }
}