package com.ticketya.entradas.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Solicitud de emisión de entradas")
public record EmisionEntradasRequest(
        @Schema(description = "ID de la orden de compra") String ordenCompraId,
        @Schema(description = "ID del cliente") String clienteId,
        @Schema(description = "ID del evento") String eventoId,
        @Schema(description = "Asientos a emitir") List<ItemEntradaEmision> asientos
) {}

