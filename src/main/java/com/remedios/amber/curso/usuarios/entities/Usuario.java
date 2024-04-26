package com.remedios.amber.curso.usuarios.entities;

import jakarta.persistence.*;
import lombok.*;

@Table(name="Usuario")
@Entity(name="usuarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String senha;
}
