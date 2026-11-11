package ticketya.pasarela_mock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import ticketya.pasarela_mock.dto.PagoRequest;
import ticketya.pasarela_mock.dto.PagoResponse;
import ticketya.pasarela_mock.dto.ReembolsoRequest;
import ticketya.pasarela_mock.dto.ReembolsoResponse;
import ticketya.pasarela_mock.service.PagoService;

@Tag(name = "Pagos", description = "Pasarela de pagos mock (cobros y reembolsos)")
@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @Operation(summary = "Procesar pago", description = "Simula un pago (APROBADO/RECHAZADO/ERROR según metodoPago)")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Pago procesado"), @ApiResponse(responseCode = "500", description = "Error técnico simulado")})
    @PostMapping("/procesar")
    public ResponseEntity<PagoResponse> procesarPago(@RequestBody PagoRequest request) {
        PagoResponse response = pagoService.procesarPago(request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Reembolsar pago", description = "Simula un reembolso")
    @ApiResponse(responseCode = "200", description = "Reembolso procesado")
    @PostMapping("/reembolsar")
    public ResponseEntity<ReembolsoResponse> reembolsarPago(@RequestBody ReembolsoRequest request) {
        ReembolsoResponse response = pagoService.reembolsarPago(request);
        return ResponseEntity.ok(response);
    }
}
