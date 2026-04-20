package mcan.reservaspadel.controllers;

import mcan.reservaspadel.dto.CreateReservaDTO;
import mcan.reservaspadel.dto.ReservaResponseDTO;
import mcan.reservaspadel.services.ReservaService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
@CrossOrigin(origins = "*")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ReservaResponseDTO crearReserva(@RequestBody CreateReservaDTO dto) {
        return reservaService.crearReserva(dto);
    }

    @GetMapping("/usuario")
    @PreAuthorize("isAuthenticated()")
    public List<ReservaResponseDTO> getReservasUsuario() {
        return reservaService.obtenerReservasUsuario();
    }

    @DeleteMapping("/{reservaId}")
    @PreAuthorize("isAuthenticated()")
    public void cancelarReserva(@PathVariable Long reservaId) {
        reservaService.cancelarReserva(reservaId);
    }
}