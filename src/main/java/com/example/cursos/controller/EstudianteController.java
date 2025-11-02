package com.example.cursos.controller;

import com.example.cursos.dto.EstudianteDTO;
import com.example.cursos.entity.Estudiante;
import com.example.cursos.service.EstudianteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
@RequiredArgsConstructor
public class EstudianteController {
    private final EstudianteService estudianteService;

    @GetMapping
    public ResponseEntity<List<EstudianteDTO>> listar() {
        // Transforma entidades a DTO y devuelve el listado
        var dtos = estudianteService.listar().stream()
                .map(e -> new EstudianteDTO(e.getId(), e.getNombre(), e.getMatricula()))
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<EstudianteDTO> crear(@RequestBody EstudianteDTO req) {
        // Crea un estudiante y devuelve el DTO resultante
        Estudiante e = estudianteService.crear(Estudiante.builder().nombre(req.nombre()).matricula(req.matricula()).build());
        return ResponseEntity.status(201).body(new EstudianteDTO(e.getId(), e.getNombre(), e.getMatricula()));
    }
}
