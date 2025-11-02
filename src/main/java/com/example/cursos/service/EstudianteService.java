package com.example.cursos.service;

import com.example.cursos.entity.Estudiante;
import com.example.cursos.repository.EstudianteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service @RequiredArgsConstructor
public class EstudianteService {
    private final EstudianteRepository estudianteRepository;

    public List<Estudiante> listar() {
        // Devuelve todos los estudiantes registrados
        return estudianteRepository.findAll();
    }

    public Estudiante crear(Estudiante e) {
        // Persiste el estudiante y devuelve la entidad creada
        return estudianteRepository.save(e);
    }

    public Estudiante buscar(Long id) {
        // Recupera un estudiante por id o lanza excepción si no existe
        return estudianteRepository.findById(id).orElseThrow();
    }
}
