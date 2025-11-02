package com.example.cursos.controller;

import com.example.cursos.dto.*;
import com.example.cursos.entity.Curso;
import com.example.cursos.service.CursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cursos")
@RequiredArgsConstructor
public class CursoController {
    private final CursoService cursoService;

    @GetMapping
    public ResponseEntity<List<CursoDTO>> listar() {
        // Lista cursos con su profesor y estudiantes
        var dtos = cursoService.listar().stream().map(c -> new CursoDTO(
                c.getId(),
                c.getNombre(),
                new ProfesorDTO(c.getProfesor().getId(), c.getProfesor().getNombre(), c.getProfesor().getEmail()),
                c.getEstudiantes().stream()
                        .map(e -> new EstudianteDTO(e.getId(), e.getNombre(), e.getMatricula()))
                        .toList()
        )).toList();
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<CursoDTO> crear(@RequestBody CrearCursoRequest req) {
        // Crea curso asociado a un profesor existente
        Curso c = cursoService.crearConProfesor(req.nombre(), req.profesorId());
        return ResponseEntity.status(201).body(new CursoDTO(
                c.getId(),
                c.getNombre(),
                new ProfesorDTO(c.getProfesor().getId(), c.getProfesor().getNombre(), c.getProfesor().getEmail()),
                List.of()
        ));
    }

    @PostMapping("/{cursoId}/estudiantes")
    public ResponseEntity<CursoDTO> asignar(@PathVariable Long cursoId, @RequestBody AsignarEstudiantesRequest req) {
        // Asigna lista de estudiantes al curso y devuelve el curso actualizado
        Curso c = cursoService.asignarEstudiantes(cursoId, req.estudianteIds());
        var estudiantes = c.getEstudiantes().stream()
                .map(e -> new EstudianteDTO(e.getId(), e.getNombre(), e.getMatricula()))
                .toList();
        return ResponseEntity.ok(new CursoDTO(
                c.getId(), c.getNombre(),
                new ProfesorDTO(c.getProfesor().getId(), c.getProfesor().getNombre(), c.getProfesor().getEmail()),
                estudiantes
        ));
    }

    @GetMapping("/por-estudiante/{estudianteId}")
    public ResponseEntity<List<CursoDTO>> porEstudiante(@PathVariable Long estudianteId) {
        // Devuelve los cursos donde participa el estudiante indicado
        var dtos = cursoService.cursosDeEstudiante(estudianteId).stream().map(c -> new CursoDTO(
                c.getId(),
                c.getNombre(),
                new ProfesorDTO(c.getProfesor().getId(), c.getProfesor().getNombre(), c.getProfesor().getEmail()),
                c.getEstudiantes().stream()
                        .map(e -> new EstudianteDTO(e.getId(), e.getNombre(), e.getMatricula()))
                        .toList()
        )).toList();
        return ResponseEntity.ok(dtos);
    }
}
