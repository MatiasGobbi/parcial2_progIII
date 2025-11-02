package com.example.cursos.service;

import com.example.cursos.entity.Curso;
import com.example.cursos.entity.Estudiante;
import com.example.cursos.entity.Profesor;
import com.example.cursos.repository.CursoRepository;
import com.example.cursos.repository.EstudianteRepository;
import com.example.cursos.repository.ProfesorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.LinkedHashSet;
import java.util.List;

@Service @RequiredArgsConstructor
public class CursoService {
    private final CursoRepository cursoRepository;
    private final ProfesorRepository profesorRepository;
    private final EstudianteRepository estudianteRepository;

    public List<Curso> listar() {
        // Devuelve todos los cursos
        return cursoRepository.findAll();
    }

    public Curso crearConProfesor(String nombre, Long profesorId) {
        // Valida el profesor y crea el curso asociado
        Profesor prof = profesorRepository.findById(profesorId).orElseThrow();
        Curso c = new Curso();
        c.setNombre(nombre);
        c.setProfesor(prof);
        c.setEstudiantes(new LinkedHashSet<>());
        return cursoRepository.save(c);
    }

    public Curso asignarEstudiantes(Long cursoId, List<Long> estudianteIds) {
        // Agrega al curso los estudiantes indicados evitando duplicados
        Curso curso = cursoRepository.findById(cursoId).orElseThrow();
        for (Long id : estudianteIds) {
            Estudiante e = estudianteRepository.findById(id).orElseThrow();
            curso.getEstudiantes().add(e);
        }
        return cursoRepository.save(curso);
    }

    public List<Curso> cursosDeEstudiante(Long estudianteId) {
        // Filtra los cursos que contienen al estudiante indicado
        Estudiante target = estudianteRepository.findById(estudianteId).orElseThrow();
        return cursoRepository.findAll().stream()
                .filter(c -> c.getEstudiantes().contains(target))
                .toList();
    }
}
