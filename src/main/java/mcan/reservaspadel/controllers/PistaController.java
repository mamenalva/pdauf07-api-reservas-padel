package mcan.reservaspadel.controllers;


import mcan.reservaspadel.dto.PistaResponseDTO;
import mcan.reservaspadel.entities.Pista;
import mcan.reservaspadel.repositories.PistaRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pistas")
@CrossOrigin(origins = "*")
public class PistaController {

    private final PistaRepository pistaRepository;

    public PistaController(PistaRepository pistaRepository) {
        this.pistaRepository = pistaRepository;
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<PistaResponseDTO> getAllPistas() {
        List<Pista> pistas = pistaRepository.findAll();
        return pistas.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    private PistaResponseDTO mapToDTO(Pista pista) {
        PistaResponseDTO dto = new PistaResponseDTO();
        dto.setId(pista.getId());
        dto.setNombre(pista.getNombre());
        return dto;
    }
}
