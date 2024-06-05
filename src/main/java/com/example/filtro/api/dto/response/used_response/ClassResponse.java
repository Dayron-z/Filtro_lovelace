package com.example.filtro.api.dto.response.used_response;


import com.example.filtro.api.dto.response.custom_response.LessonResponseToClassResponse;
import com.example.filtro.api.dto.response.custom_response.StudentResponseToClassResponse;
import com.example.filtro.domain.entities.Lesson;
import com.example.filtro.domain.entities.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClassResponse {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime created_at;
    private Boolean active;
    private List<StudentResponseToClassResponse> students;
    private List<LessonResponseToClassResponse> lessons;
}
