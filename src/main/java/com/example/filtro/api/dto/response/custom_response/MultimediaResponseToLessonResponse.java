package com.example.filtro.api.dto.response.custom_response;


import com.example.filtro.domain.entities.Lesson;
import com.example.filtro.util.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MultimediaResponseToLessonResponse {
    private Long id;
    private Type type;
    private String url;
    private LocalDateTime created_at;
    private Boolean active;
}
