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
public class ClassRequest {
    @NotBlank(message = "EL nombre no puede estar en blanco")
    @Size(max = 100, message = "No puede exceder los 100 caracteres")
    private String name;
    @NotBlank(message = "La descripcion no puede estar en blanco")
    private String description;
}


