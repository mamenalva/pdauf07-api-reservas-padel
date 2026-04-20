package mcan.reservaspadel.controllers;


import mcan.reservaspadel.entities.Pista;
import mcan.reservaspadel.repositories.PistaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pistas")
@CrossOrigin(origins = "*")
public class PistaController {

    private final PistaRepository pistaRepository;

    public PistaController(PistaRepository pistaRepository) {
        this.pistaRepository = pistaRepository;
    }

    @GetMapping
    public List<Pista> getAllPistas() {
        return pistaRepository.findAll();
    }
}
