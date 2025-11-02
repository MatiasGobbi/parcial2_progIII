package com.example.cursos.repository;

import com.example.cursos.entity.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

// Expone operaciones CRUD para la entidad Profesor
public interface ProfesorRepository extends JpaRepository<Profesor, Long> {}
