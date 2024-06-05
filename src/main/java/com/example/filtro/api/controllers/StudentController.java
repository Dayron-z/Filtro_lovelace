package com.example.filtro.api.controllers;


import com.example.filtro.api.dto.request.used_request.ClassRequest;
import com.example.filtro.api.dto.request.used_request.StudentRequest;
import com.example.filtro.api.dto.response.used_response.ClassResponse;
import com.example.filtro.api.dto.response.used_response.StudentResponse;
import com.example.filtro.infrastructure.abstract_services.IClassService;
import com.example.filtro.infrastructure.abstract_services.IStudentService;
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
@RequestMapping(path = "/student")
@Tag(name = "student", description = "Operaciones relacionadas con los estudiantes")
public class StudentController {
    @Autowired
    private final IStudentService iStudentService;

    @PostMapping
    public ResponseEntity<StudentResponse> create(@Validated @RequestBody StudentRequest studentRequest){
        return ResponseEntity.ok(this.iStudentService.create(studentRequest));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<StudentResponse> get(@PathVariable Long id){
        return ResponseEntity.ok(this.iStudentService.get(id));
    }


    @GetMapping
    public ResponseEntity<Page<StudentResponse>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "8") int size,
            @RequestHeader(required = false) SortType sortType){

        if (Objects.isNull(sortType)){
            sortType = SortType.NONE;
        }

        return ResponseEntity.ok(this.iStudentService.getAll(page - 1, size, sortType));
    }

    @PatchMapping(path = "/students/{id}/disable")
    public ResponseEntity<StudentResponse> inhabilitar(@PathVariable Long id){
        return ResponseEntity.ok(this.iStudentService.inhabilitar(id));
    }


}
