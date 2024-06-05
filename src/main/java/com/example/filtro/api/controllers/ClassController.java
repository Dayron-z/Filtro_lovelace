package com.example.filtro.api.controllers;

import com.example.filtro.api.dto.request.used_request.ClassRequest;
import com.example.filtro.api.dto.response.used_response.ClassResponse;
import com.example.filtro.infrastructure.abstract_services.IClassService;
import com.example.filtro.util.enums.SortType;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/class")
@Tag(name = "class", description = "Operaciones relacionadas con las clases")
public class ClassController {

    @Autowired
    private final IClassService classService;

    @PostMapping
    public ResponseEntity<ClassResponse> create(@Validated @RequestBody ClassRequest classRequest){
        return ResponseEntity.ok(this.classService.create(classRequest));
    }


    @GetMapping(path = "/{id}")
    public ResponseEntity<ClassResponse> get(@PathVariable Long id){
        return ResponseEntity.ok(this.classService.get(id));
    }


    @GetMapping
    public ResponseEntity<Page<ClassResponse>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "8") int size,
            @RequestHeader(required = false) SortType sortType){

        if (Objects.isNull(sortType)){
            sortType = SortType.NONE;
        }

        return ResponseEntity.ok(this.classService.getAll(page - 1, size, sortType));
    }

}
