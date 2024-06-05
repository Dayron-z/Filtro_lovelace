package com.example.filtro.api.dto.response.used_response;


import com.example.filtro.api.dto.response.custom_response.ClassResponseToLessonResponse;
import com.example.filtro.api.dto.response.custom_response.MultimediaResponseToLessonResponse;
import com.example.filtro.domain.entities.ClassEntity;
import com.example.filtro.domain.entities.Multimedia;
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
public class LessonResponse {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime created_at;
    private Boolean active;
    private ClassResponseToLessonResponse aClass;
    private List<MultimediaResponseToLessonResponse> multimediaList;
}
