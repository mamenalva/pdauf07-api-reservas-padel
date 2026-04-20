package mcan.reservaspadel.services;

import mcan.reservaspadel.dto.CreateReservaDTO;
import mcan.reservaspadel.dto.ReservaResponseDTO;

import java.util.List;

public interface ReservaService {

    ReservaResponseDTO crearReserva(CreateReservaDTO dto);

    List<ReservaResponseDTO> obtenerReservasUsuario();

    void cancelarReserva(Long reservaId);
}