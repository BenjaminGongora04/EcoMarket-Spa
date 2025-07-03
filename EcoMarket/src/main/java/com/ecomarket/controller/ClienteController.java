package com.ecomarket.controller;

import com.ecomarket.dto.ClienteDTO;
import com.ecomarket.dto.PedidoDTO;
import com.ecomarket.model.Cliente;
import com.ecomarket.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/registro")
    public ResponseEntity<ClienteDTO> registrarCliente(@RequestBody ClienteDTO clienteDTO) {
        return ResponseEntity.ok(clienteService.registrarCliente(clienteDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> obtenerCliente(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.obtenerClientePorId(id));
    }

    @GetMapping("/{id}/pedidos")
    public ResponseEntity<List<PedidoDTO>> obtenerPedidosCliente(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.obtenerPedidosPorCliente(id));
    }

    @GetMapping("/{id}/favoritos")
    public ResponseEntity<List<PerfumeDTO>> obtenerPerfumesFavoritos(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.obtenerPerfumesFavoritos(id));
    }

    @PostMapping("/{id}/favoritos/{perfumeId}")
    public ResponseEntity<Void> agregarFavorito(
            @PathVariable Long id,
            @PathVariable Long perfumeId) {
        clienteService.agregarPerfumeFavorito(id, perfumeId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/favoritos/{perfumeId}")
    public ResponseEntity<Void> eliminarFavorito(
            @PathVariable Long id,
            @PathVariable Long perfumeId) {
        clienteService.eliminarPerfumeFavorito(id, perfumeId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/preferencias")
    public ResponseEntity<ClienteDTO> actualizarPreferencias(
            @PathVariable Long id,
            @RequestBody PreferenciasDTO preferencias) {
        return ResponseEntity.ok(clienteService.actualizarPreferencias(id, preferencias));
    }

    @GetMapping("/{id}/recomendaciones")
    public ResponseEntity<List<PerfumeDTO>> obtenerRecomendaciones(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.obtenerPerfumesRecomendados(id));
    }
}