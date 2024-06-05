package com.example.filtro.api.dto.response.used_response;


import com.example.filtro.api.dto.response.custom_response.ClassResponseToStudentResponse;
import com.example.filtro.domain.entities.ClassEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse {
    private Long id;
    private String name;
    private String email;
    private LocalDateTime created_at;
    private Boolean active;
    private ClassResponseToStudentResponse aClass;
}
