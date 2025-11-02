package com.example.cursos.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "profesores")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Profesor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String email;
}
