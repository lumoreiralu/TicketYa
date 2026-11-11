package ticketya.compras_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ticketya.compras_service.dto.ConfirmacionPagoResponse;
import ticketya.compras_service.dto.ConfirmarPagoRequest;
import ticketya.compras_service.dto.ReservaRequest;
import ticketya.compras_service.dto.ReservaResponse;
import ticketya.compras_service.repository.BloqueoAsientoRepository;
import ticketya.compras_service.service.ReservaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.UUID;

@Tag(name = "Compras", description = "Reserva y compra de entradas")
@RestController
@RequestMapping("/api/compras")
public class CompraController {

    private final ReservaService reservaService;
    private BloqueoAsientoRepository bloqueoAsientoRepo;

    public CompraController(ReservaService reservaService, BloqueoAsientoRepository bloqueoAsientoRepo) {
        this.reservaService = reservaService;
        this.bloqueoAsientoRepo = bloqueoAsientoRepo;
    }

    @Operation(summary = "Reservar asientos", description = "Reserva asientos por 10 minutos y bloquea la selección a otros usuarios")
    @ApiResponses({@ApiResponse(responseCode = "201", description = "Reserva creada"), @ApiResponse(responseCode = "400", description = "Datos inválidos"), @ApiResponse(responseCode = "409", description = "Asiento ya tomado"), @ApiResponse(responseCode = "422", description = "Supera límite de entradas")})
    @PostMapping("/reservar")
    public ResponseEntity<ReservaResponse> reservar(
            @RequestHeader(value = "Idempotency-Key", required = false) String idempotencyKey,
            @RequestBody ReservaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reservaService.reservar(request, idempotencyKey));
    }

    @Operation(summary = "Confirmar pago", description = "Procesa el pago y emite las entradas")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Compra completada"), @ApiResponse(responseCode = "402", description = "Pago rechazado"), @ApiResponse(responseCode = "404", description = "Orden no encontrada"), @ApiResponse(responseCode = "409", description = "Orden expirada o en proceso")})
    @PostMapping("/{id}/confirmar-pago")
    public ResponseEntity<ConfirmacionPagoResponse> confirmarPago(
            @PathVariable UUID id,
            @RequestBody(required = false) ConfirmarPagoRequest request) {
        return ResponseEntity.ok(reservaService.confirmarPago(id, request));
    }

    @Operation(summary = "Consultar orden", description = "Consulta el estado actual de una orden")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Estado de la orden"), @ApiResponse(responseCode = "404", description = "Orden no encontrada")})
    @GetMapping("/{id}")
    public ResponseEntity<ConfirmacionPagoResponse> consultarOrden(@PathVariable UUID id) {
        return ResponseEntity.ok(reservaService.consultarOrden(id));
    }

    public record LiberarAsientoRequest(String eventoId, String sectorId, String fila, Integer asiento){}

    @PostMapping("/asientos/liberar")
    @Transactional
    public ResponseEntity<Void> liberarAsiento(@RequestBody LiberarAsientoRequest req){
        String claveAsiento = req.eventoId() + "|" + req.sectorId() + "|" + req.fila() + "|" + req.asiento();
        bloqueoAsientoRepo.deleteByClaveAsiento(claveAsiento);
        return ResponseEntity.ok().build();
    }
}
