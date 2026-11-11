package ticketya.compras_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import ticketya.compras_service.model.EstadoOrden;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Schema(description = "Respuesta de una reserva temporal")
public record ReservaResponse(
        @Schema(description = "ID de la orden") UUID ordenId,
        @Schema(description = "Estado de la orden") EstadoOrden estado,
        @Schema(description = "Fecha de expiración de la reserva") LocalDateTime expiraEn,
        @Schema(description = "Monto total") BigDecimal montoTotal,
        @Schema(description = "Segundos restantes de la reserva") long segundosRestantes) {}