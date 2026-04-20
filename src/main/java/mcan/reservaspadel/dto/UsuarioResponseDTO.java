package mcan.reservaspadel.dto;

import lombok.*;
import mcan.reservaspadel.entities.Role;

@Getter
@Setter
public class UsuarioResponseDTO {

    private Long id;
    private String nombre;
    private String apellidos;
    private String email;
    private String telefono;
    
    private Role role;
}
