package com.ticketya.entradas.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Item de entrada a emitir")
public record ItemEntradaEmision(
        @Schema(description = "Nombre del sector", example = "Platea Preferencial") String sectorNombre,
        @Schema(description = "Fila") String fila,
        @Schema(description = "Asiento") Integer asiento) {}

