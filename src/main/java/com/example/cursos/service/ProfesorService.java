package com.example.cursos.service;

import com.example.cursos.entity.Profesor;
import com.example.cursos.repository.ProfesorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service @RequiredArgsConstructor
public class ProfesorService {
    private final ProfesorRepository profesorRepository;

    public List<Profesor> listar() {
        // Devuelve todos los profesores registrados
        return profesorRepository.findAll();
    }

    public Profesor crear(Profesor p) {
        // Persiste el profesor y devuelve la entidad creada
        return profesorRepository.save(p);
    }

    public Profesor buscar(Long id) {
        // Recupera un profesor por id o lanza excepción si no existe
        return profesorRepository.findById(id).orElseThrow();
    }
}
