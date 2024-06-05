package com.example.filtro.domain.repositories;

import com.example.filtro.domain.entities.ClassEntity;
import com.example.filtro.domain.entities.Lesson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ClassRepository extends JpaRepository<ClassEntity, Long> {
}
