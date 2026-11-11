package ticketya.compras_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Solicitud de confirmación de pago")
public record ConfirmarPagoRequest(
        @Schema(description = "Método de pago", example = "TARJETA") String metodoPago) {}

