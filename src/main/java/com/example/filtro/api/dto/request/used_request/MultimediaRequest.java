package com.example.filtro.api.dto.request.used_request;


import com.example.filtro.util.enums.Type;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MultimediaRequest {
    @NotBlank(message = "EL type no puede estar en blanco")
    private Type type;
    @NotBlank(message = "La url no puede estar en blanco")
    private String url;
    @NotBlank(message = "La lesson_id no puede estar en blanco")
    private Long lesson_id;
}
