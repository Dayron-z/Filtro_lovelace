package com.example.filtro.api.dto.request.used_request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LessonRequest {
    @NotBlank(message = "EL titulo no puede estar en blanco")
    @Size(max = 100, message = "No puede exceder los 100 caracteres")
    private String title;
    @NotBlank(message = "EL contenido no puede estar en blanco")
    private String content;
    @NotBlank(message = "EL class_id no puede estar en blanco")
    private Long class_id;
}
