package ticketya.compras_service.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Solicitud de pago a la pasarela")
public record PagoRequest(
        @Schema(description = "Monto a cobrar", example = "30000.00") BigDecimal monto,
        @Schema(description = "ID de la orden", example = "uuid-de-la-orden") String ordenId,
        @Schema(description = "Método de pago", example = "TARJETA") String metodoPago) {}

