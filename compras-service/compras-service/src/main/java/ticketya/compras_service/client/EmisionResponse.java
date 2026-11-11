package ticketya.compras_service.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Respuesta de emisión de entradas")
public record EmisionResponse(String ordenCompraId, List<EntradaDTO> entradas) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record EntradaDTO(String id,
                              String ordenCompraId,
                              String clienteId,
                              String eventoId,
                              String sectorNombre,
                              String fila,
                              Integer asiento,
                              String codigoQrHash,
                              String estado,
                              String fechaEmision) {}
}

