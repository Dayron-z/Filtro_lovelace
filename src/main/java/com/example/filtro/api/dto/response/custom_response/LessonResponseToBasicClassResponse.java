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
public class LessonResponseToBasicClassResponse {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime created_at;
    private Boolean active;
}
