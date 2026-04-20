package mcan.reservaspadel.services;

import mcan.reservaspadel.dto.LoginRequestDTO;
import mcan.reservaspadel.dto.RegisterRequestDTO;
import mcan.reservaspadel.dto.UsuarioResponseDTO;

public interface AuthService {

    UsuarioResponseDTO register(RegisterRequestDTO request);

    String authenticate(LoginRequestDTO request);
}
