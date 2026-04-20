package mcan.reservaspadel.dto;

import lombok.*;

@Getter
@Setter
public class UsuarioResponseDTO {

    private Long id;
    private String nombre;
    private String apellidos;
    private String email;
    private String telefono;
}
