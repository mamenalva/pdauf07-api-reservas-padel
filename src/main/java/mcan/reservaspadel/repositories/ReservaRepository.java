package mcan.reservaspadel.repositories;

import mcan.reservaspadel.entities.Pista;
import mcan.reservaspadel.entities.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    List<Reserva> findByUsuarioId(Long usuarioId);

    boolean existsByPistaAndFechaAndHorario(Pista pista, LocalDate fecha, String horario);

    List<Reserva> findByPistaAndFecha(Pista pista, LocalDate fecha);
}
