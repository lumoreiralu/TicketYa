package ticketya.compras_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Item de reserva (sector, fila y asiento)")
public record ItemReserva(
        @Schema(description = "ID del sector", example = "SEC-PLATEA-A") String sectorId,
        @Schema(description = "Fila (null para sectores generales)", example = "1") String fila,
        @Schema(description = "Asiento (null para sectores generales)", example = "5") Integer asiento) {}