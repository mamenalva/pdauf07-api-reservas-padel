package mcan.reservaspadel.dto;

import lombok.*;

@Getter
@Setter
public class RegisterRequestDTO {

    private String nombre;
    private String apellidos;
    private String telefono;
    private String email;
    private String password;
}