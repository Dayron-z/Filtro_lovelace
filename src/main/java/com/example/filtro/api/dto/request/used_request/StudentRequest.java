package com.example.filtro.api.dto.request.used_request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequest {
    @NotBlank(message = "EL nombre no puede estar en blanco")
    private String name;
    @NotBlank(message = "EL email no puede estar en blanco")
    private String email;
    @NotNull(message = "EL class_id no puede estar en blanco")
    private Long class_id;
}
