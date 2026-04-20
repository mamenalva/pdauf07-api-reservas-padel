package mcan.reservaspadel.dto;

import lombok.*;
import mcan.reservaspadel.entities.Role;

@Getter
@Setter
public class RegisterRequestDTO {

    private String nombre;
    private String apellidos;
    private String telefono;
    private String email;
    private String password;
    
    private Role role;
}