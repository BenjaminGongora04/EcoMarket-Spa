package com.ecomarket.service;

import com.ecomarket.dto.CategoriaDTO;
import com.ecomarket.exception.ResourceNotFoundException;
import com.ecomarket.exception.BusinessRuleException;
import com.ecomarket.model.Categoria;
import com.ecomarket.repository.CategoriaRepository;
import com.ecomarket.repository.PerfumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final PerfumeRepository perfumeRepository;

    @Autowired
    public CategoriaService(CategoriaRepository categoriaRepository,
                            PerfumeRepository perfumeRepository) {
        this.categoriaRepository = categoriaRepository;
        this.perfumeRepository = perfumeRepository;
    }

    @Transactional(readOnly = true)
    public List<CategoriaDTO> getAllCategorias() {
        return categoriaRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CategoriaDTO> getActiveCategorias() {
        return categoriaRepository.findByActivoTrue()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CategoriaDTO getCategoriaById(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con id: " + id));
        return convertToDTO(categoria);
    }

    @Transactional
    public CategoriaDTO createCategoria(CategoriaDTO categoriaDTO) {
        // Validar que no exista una categoría con el mismo nombre
        if (categoriaRepository.findByNombreIgnoreCase(categoriaDTO.getNombre()).isPresent()) {
            throw new BusinessRuleException("Ya existe una categoría con el nombre: " + categoriaDTO.getNombre());
        }

        Categoria categoria = convertToEntity(categoriaDTO);
        categoria.setActivo(true); // Por defecto activa al crear
        Categoria savedCategoria = categoriaRepository.save(categoria);
        return convertToDTO(savedCategoria);
    }

    @Transactional
    public CategoriaDTO updateCategoria(Long id, CategoriaDTO categoriaDTO) {
        Categoria existingCategoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con id: " + id));

        // Validar nombre único si cambió
        if (!existingCategoria.getNombre().equalsIgnoreCase(categoriaDTO.getNombre()) &&
                categoriaRepository.findByNombreIgnoreCase(categoriaDTO.getNombre()).isPresent()) {
            throw new BusinessRuleException("Ya existe otra categoría con el nombre: " + categoriaDTO.getNombre());
        }

        // Actualizar campos
        existingCategoria.setNombre(categoriaDTO.getNombre());
        existingCategoria.setDescripcion(categoriaDTO.getDescripcion());
        existingCategoria.setActivo(categoriaDTO.isActivo());

        Categoria updatedCategoria = categoriaRepository.save(existingCategoria);
        return convertToDTO(updatedCategoria);
    }

    @Transactional
    public void deleteCategoria(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con id: " + id));

        // Validar que no tenga productos asociados
        Long countProductos = perfumeRepository.countByCategoriaId(id);
        if (countProductos > 0) {
            throw new BusinessRuleException("No se puede eliminar la categoría porque tiene " + countProductos + " productos asociados");
        }

        categoriaRepository.delete(categoria);
    }

    @Transactional
    public CategoriaDTO toggleCategoriaStatus(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con id: " + id));

        categoria.setActivo(!categoria.isActivo());
        Categoria updatedCategoria = categoriaRepository.save(categoria);
        return convertToDTO(updatedCategoria);
    }

    @Transactional(readOnly = true)
    public List<CategoriaDTO> searchCategorias(String query) {
        return categoriaRepository.findByNombreContainingIgnoreCase(query)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Métodos de conversión
    private CategoriaDTO convertToDTO(Categoria categoria) {
        CategoriaDTO dto = new CategoriaDTO();
        dto.setId(categoria.getId());
        dto.setNombre(categoria.getNombre());
        dto.setDescripcion(categoria.getDescripcion());
        dto.setActivo(categoria.isActivo());
        return dto;
    }

    private Categoria convertToEntity(CategoriaDTO categoriaDTO) {
        Categoria categoria = new Categoria();
        categoria.setNombre(categoriaDTO.getNombre());
        categoria.setDescripcion(categoriaDTO.getDescripcion());
        categoria.setActivo(categoriaDTO.isActivo());
        return categoria;
    }
}