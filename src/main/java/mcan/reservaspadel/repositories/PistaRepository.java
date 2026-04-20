package mcan.reservaspadel.repositories;

import mcan.reservaspadel.entities.Pista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PistaRepository extends JpaRepository<Pista, Long> {

    Optional<Pista> findByNombre(String nombre);

    boolean existsByNombre(String nombre);
}
