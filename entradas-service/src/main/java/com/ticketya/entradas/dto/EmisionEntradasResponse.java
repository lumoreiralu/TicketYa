package com.ticketya.entradas.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Respuesta de emisión de entradas")
public record EmisionEntradasResponse(
        @Schema(description = "ID de la orden de compra") String ordenCompraId,
        @Schema(description = "Entradas emitidas") List<EntradaResponseDTO> entradas
) {}

