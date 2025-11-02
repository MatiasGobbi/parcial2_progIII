package com.example.cursos.dto;

// Recibe el nombre del curso y el id del profesor existente
public record CrearCursoRequest(String nombre, Long profesorId) {}
