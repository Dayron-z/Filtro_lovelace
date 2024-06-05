package com.example.filtro.domain.repositories;

import com.example.filtro.domain.entities.ClassEntity;
import com.example.filtro.domain.entities.Multimedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MultimediaRepository extends JpaRepository<Multimedia, Long> {
}
