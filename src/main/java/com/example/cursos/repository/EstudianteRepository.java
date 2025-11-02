package com.example.cursos.repository;

import com.example.cursos.entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

// Expone operaciones CRUD para la entidad Estudiante
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {}
