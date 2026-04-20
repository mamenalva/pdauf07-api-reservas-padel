package mcan.reservaspadel.controllers;

import mcan.reservaspadel.dto.CreateReservaDTO;
import mcan.reservaspadel.dto.ReservaResponseDTO;
import mcan.reservaspadel.services.ReservaService;
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
    public ReservaResponseDTO crearReserva(@RequestBody CreateReservaDTO dto) {
        return reservaService.crearReserva(dto);
    }

    @GetMapping("/usuario/{id}")
    public List<ReservaResponseDTO> getReservasUsuario(@PathVariable Long id) {
        return reservaService.obtenerReservasUsuario(id);
    }

    @DeleteMapping("/{reservaId}")
    public void cancelarReserva(
            @PathVariable Long reservaId,
            @RequestParam Long usuarioId
    ) {
        reservaService.cancelarReserva(reservaId, usuarioId);
    }
}