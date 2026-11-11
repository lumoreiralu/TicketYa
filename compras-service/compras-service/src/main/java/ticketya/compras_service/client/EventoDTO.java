package ticketya.compras_service.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "DTO de evento recibido de eventos-service")
public record EventoDTO(
        String id,
        String estado,
        List<PrecioSectorDTO> preciosSectores,
        List<SectorInfoDTO> sectores
) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record PrecioSectorDTO(String idSector, BigDecimal precio) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record SectorInfoDTO(String sectorId, String nombre, String tipo, Integer capacidadMaxima) {}
}
