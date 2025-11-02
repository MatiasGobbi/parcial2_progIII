package com.example.cursos.repository;

import com.example.cursos.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

// Expone operaciones CRUD para la entidad Curso
public interface CursoRepository extends JpaRepository<Curso, Long> {}
