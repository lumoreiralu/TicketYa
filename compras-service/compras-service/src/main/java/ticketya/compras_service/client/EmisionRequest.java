package ticketya.compras_service.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Solicitud de emisión de entradas")
public record EmisionRequest(String ordenCompraId,
                              String clienteId,
                              String eventoId,
                              List<AsientoEntrada> asientos) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record AsientoEntrada(String sectorNombre, String fila, Integer asiento) {}
}

