package com.example.cursos.dto;

import java.util.List;

// Recibe una lista de ids de estudiantes para asociar al curso
public record AsignarEstudiantesRequest(List<Long> estudianteIds) {}
