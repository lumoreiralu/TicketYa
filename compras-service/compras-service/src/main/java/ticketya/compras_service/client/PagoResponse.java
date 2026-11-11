package ticketya.compras_service.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;

@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Respuesta de la pasarela de pago")
public record PagoResponse(
        @Schema(description = "ID de transacción") String transaccionId,
        @Schema(description = "Estado del pago", example = "APROBADO") String estado,
        @Schema(description = "Mensaje") String mensaje) {}

