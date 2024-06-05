package com.example.filtro.domain.repositories;

import com.example.filtro.domain.entities.ClassEntity;
import com.example.filtro.domain.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
