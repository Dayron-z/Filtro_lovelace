package com.example.filtro.domain.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(nullable = false, length = 100)
    private String email;
    @Column(nullable = false)
    private LocalDateTime created_at;
    @Column(nullable = false)
    private Boolean active;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name =  "class_id" , referencedColumnName = "id")
    private ClassEntity aClass;
}
