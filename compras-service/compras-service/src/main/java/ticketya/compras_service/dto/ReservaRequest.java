package ticketya.compras_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Solicitud de reserva de entradas")
public record ReservaRequest(
        @Schema(description = "ID del cliente", example = "CLI-1234") String clienteId,
        @Schema(description = "ID del evento", example = "EVT-001") String eventoId,
        @Schema(description = "Items a reservar (sector, fila, asiento)") List<ItemReserva> items) {}