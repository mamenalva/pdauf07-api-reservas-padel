package mcan.reservaspadel.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pistas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nombre; // A, B, C, D
}
