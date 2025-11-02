package com.example.cursos.controller;

import com.example.cursos.dto.ProfesorDTO;
import com.example.cursos.entity.Profesor;
import com.example.cursos.service.ProfesorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/profesores")
@RequiredArgsConstructor
public class ProfesorController {
    private final ProfesorService profesorService;

    @GetMapping
    public ResponseEntity<List<ProfesorDTO>> listar() {
        // Transforma entidades a DTO y devuelve el listado
        var dtos = profesorService.listar().stream()
                .map(p -> new ProfesorDTO(p.getId(), p.getNombre(), p.getEmail()))
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<ProfesorDTO> crear(@RequestBody ProfesorDTO req) {
        // Crea un profesor y devuelve el DTO resultante
        Profesor p = profesorService.crear(Profesor.builder().nombre(req.nombre()).email(req.email()).build());
        return ResponseEntity.status(201).body(new ProfesorDTO(p.getId(), p.getNombre(), p.getEmail()));
    }
}
