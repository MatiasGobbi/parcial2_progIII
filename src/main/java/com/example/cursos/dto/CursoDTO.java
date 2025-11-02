package com.example.cursos.dto;

import java.util.List;

// Resume un curso con su profesor y lista de estudiantes
public record CursoDTO(Long id, String nombre, ProfesorDTO profesor, List<EstudianteDTO> estudiantes) {}
