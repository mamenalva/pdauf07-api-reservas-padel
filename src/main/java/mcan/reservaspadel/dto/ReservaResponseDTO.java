package mcan.reservaspadel.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
public class ReservaResponseDTO {

    private Long id;
    private LocalDate fecha;
    private String horario;
    private String pistaNombre;
    private String usuarioEmail;
}
