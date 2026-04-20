package mcan.reservaspadel.services;

import mcan.reservaspadel.dto.CreateReservaDTO;
import mcan.reservaspadel.dto.ReservaResponseDTO;
import mcan.reservaspadel.entities.Pista;
import mcan.reservaspadel.entities.Reserva;
import mcan.reservaspadel.entities.Usuario;
import mcan.reservaspadel.repositories.PistaRepository;
import mcan.reservaspadel.repositories.ReservaRepository;
import mcan.reservaspadel.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservaServiceImpl implements ReservaService {

    private final ReservaRepository reservaRepository;
    private final UsuarioRepository usuarioRepository;
    private final PistaRepository pistaRepository;

    public ReservaServiceImpl(ReservaRepository reservaRepository,
                              UsuarioRepository usuarioRepository,
                              PistaRepository pistaRepository) {
        this.reservaRepository = reservaRepository;
        this.usuarioRepository = usuarioRepository;
        this.pistaRepository = pistaRepository;
    }

    @Override
    public ReservaResponseDTO crearReserva(CreateReservaDTO dto) {

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Pista pista = pistaRepository.findById(dto.getPistaId())
                .orElseThrow(() -> new RuntimeException("Pista no encontrada"));

        boolean existe = reservaRepository.existsByPistaAndFechaAndHorario(
                pista,
                dto.getFecha(),
                dto.getHorario()
        );

        if (existe) {
            throw new RuntimeException("Ya existe una reserva en ese horario");
        }

        Reserva reserva = new Reserva();
        reserva.setUsuario(usuario);
        reserva.setPista(pista);
        reserva.setFecha(dto.getFecha());
        reserva.setHorario(dto.getHorario());

        Reserva guardada = reservaRepository.save(reserva);

        return mapToDTO(guardada);
    }

    @Override
    public List<ReservaResponseDTO> obtenerReservasUsuario(Long usuarioId) {

        return reservaRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void cancelarReserva(Long reservaId, Long usuarioId) {

        Reserva reserva = reservaRepository.findById(reservaId)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        if (!reserva.getUsuario().getId().equals(usuarioId)) {
            throw new RuntimeException("No puedes cancelar esta reserva");
        }

        reservaRepository.delete(reserva);
    }

    // MAPPER (ENTIDAD → DTO)
    private ReservaResponseDTO mapToDTO(Reserva reserva) {
        ReservaResponseDTO dto = new ReservaResponseDTO();
        dto.setId(reserva.getId());
        dto.setFecha(reserva.getFecha());
        dto.setHorario(reserva.getHorario());
        dto.setPistaNombre(reserva.getPista().getNombre());
        dto.setUsuarioEmail(reserva.getUsuario().getEmail());
        return dto;
    }
}