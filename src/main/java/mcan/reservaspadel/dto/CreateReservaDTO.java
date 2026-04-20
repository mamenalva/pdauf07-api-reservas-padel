package mcan.reservaspadel.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
public class CreateReservaDTO {

    private Long usuarioId;
    private Long pistaId;
    private LocalDate fecha;
    private String horario;
}
