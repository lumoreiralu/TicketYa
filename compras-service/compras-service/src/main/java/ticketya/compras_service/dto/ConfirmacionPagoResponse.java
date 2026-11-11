package ticketya.compras_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import ticketya.compras_service.client.EmisionResponse.EntradaDTO;
import ticketya.compras_service.model.EstadoOrden;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Schema(description = "Respuesta de confirmación de pago")
public record ConfirmacionPagoResponse(
        @Schema(description = "ID de la orden") UUID ordenId,
        @Schema(description = "Estado de la orden") EstadoOrden estado,
        @Schema(description = "ID de transacción de pago") String transaccionPagoId,
        @Schema(description = "Monto total") BigDecimal montoTotal,
        @Schema(description = "Método de pago") String metodoPago,
        @Schema(description = "Entradas emitidas") List<EntradaDTO> entradas,
        @Schema(description = "Mensaje") String mensaje
) {}

