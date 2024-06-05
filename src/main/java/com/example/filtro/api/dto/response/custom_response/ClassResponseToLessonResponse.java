package com.example.filtro.api.dto.response.custom_response;


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
public class ClassResponseToLessonResponse {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime created_at;
    private Boolean active;
}
