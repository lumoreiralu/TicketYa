package com.ticketya.entradas.dto;

import com.ticketya.entradas.model.EstadoEntrada;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.UUID;

@Schema(description = "Entrada emitida con código QR")
public record EntradaResponseDTO(
        UUID id,
        String ordenCompraId,
        String clienteId,
        String eventoId,
        String sectorNombre,
        String fila,
        Integer asiento,
        String codigoQrHash,
        EstadoEntrada estado,
        LocalDateTime fechaEmision
) {}

