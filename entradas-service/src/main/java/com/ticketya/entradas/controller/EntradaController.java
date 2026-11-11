package com.ticketya.entradas.controller;

import com.ticketya.entradas.dto.EmisionEntradasRequest;
import com.ticketya.entradas.dto.EmisionEntradasResponse;
import com.ticketya.entradas.dto.EntradaResponseDTO;
import com.ticketya.entradas.model.SolicitudDevolucion;
import com.ticketya.entradas.service.EntradaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.UUID;

@Tag(name = "Entradas", description = "Emisión y consulta de entradas con QR")
@RestController
@RequestMapping("/api/entradas")
public class EntradaController {

    private final EntradaService entradaService;

    public EntradaController(EntradaService entradaService) {
        this.entradaService = entradaService;
    }

    @Operation(summary = "Emitir entradas", description = "Emite entradas con código QR para una orden (idempotente por ordenCompraId)")
    @ApiResponse(responseCode = "201", description = "Entradas emitidas")
    @PostMapping("/emitir")
    public ResponseEntity<EmisionEntradasResponse> emitirEntradas(@RequestBody EmisionEntradasRequest request) {
        EmisionEntradasResponse response = entradaService.emitirEntradas(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Entradas por orden", description = "Lista las entradas emitidas para una orden")
    @ApiResponse(responseCode = "200", description = "Lista de entradas")
    @GetMapping("/orden/{ordenCompraId}")
    public ResponseEntity<List<EntradaResponseDTO>> obtenerPorOrden(@PathVariable String ordenCompraId) {
        List<EntradaResponseDTO> entradas = entradaService.obtenerPorOrden(ordenCompraId);
        return ResponseEntity.ok(entradas);
    }

    @PostMapping("/{id}/devolucion")
    public ResponseEntity<SolicitudDevolucion> solicitarDevolucion(@PathVariable UUID id, @RequestParam String clienteId, @RequestParam(required = false) String motivo){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(entradaService.solicitarDevolucion(id, clienteId, motivo));
    }

    @PostMapping("/devoluciones/{solicitudId}/aprobar")
    public ResponseEntity<Void> aprobarDevolucion(@PathVariable UUID solicitudId, @RequestParam(required = false, defaultValue = "Aprobada") String observacion){
        entradaService.aprobarDevolucion(solicitudId, observacion);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/eventos/{eventoId}/cancelar")
    public ResponseEntity<String> cancelarEntradasEvento(@PathVariable String eventoId){
        int canceladas = entradaService.cancelarEntradasPorEvento(eventoId);
        return ResponseEntity.ok("Se cancelaron" + canceladas + " entradas y se liberaron sus asientos");
    }

    @Operation(summary = "Entradas por cliente", description = "Lista las entradas de un cliente")
    @ApiResponse(responseCode = "200", description = "Lista de entradas")
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<EntradaResponseDTO>> obtenerPorCliente(@PathVariable String clienteId) {
        List<EntradaResponseDTO> entradas = entradaService.obtenerPorCliente(clienteId);
        return ResponseEntity.ok(entradas);
    }
}

