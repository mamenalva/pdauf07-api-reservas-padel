package mcan.reservaspadel.controllers;

import mcan.reservaspadel.dto.LoginRequestDTO;
import mcan.reservaspadel.dto.RegisterRequestDTO;
import mcan.reservaspadel.dto.UsuarioResponseDTO;
import mcan.reservaspadel.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<UsuarioResponseDTO> register(@RequestBody RegisterRequestDTO request) {
        UsuarioResponseDTO response = authService.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO request) {
        String token = authService.authenticate(request);
        return ResponseEntity.ok(token);
    }
}
