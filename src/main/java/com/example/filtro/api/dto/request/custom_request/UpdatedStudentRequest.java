package com.example.filtro.api.dto.request.custom_request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdatedStudentRequest {
    @NotBlank(message = "EL nombre no puede estar en blanco")
    private String name;
    @NotBlank(message = "EL email no puede estar en blanco")
    private String email;
}
