package com.example.filtro.api.controllers;

import com.example.filtro.api.dto.request.custom_request.UpdatedLessonRequest;
import com.example.filtro.api.dto.request.custom_request.UpdatedStudentRequest;
import com.example.filtro.api.dto.request.used_request.LessonRequest;
import com.example.filtro.api.dto.request.used_request.StudentRequest;
import com.example.filtro.api.dto.response.used_response.LessonResponse;
import com.example.filtro.api.dto.response.used_response.StudentResponse;
import com.example.filtro.infrastructure.abstract_services.ILessonService;
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
@RequestMapping(path = "/lesson")
@Tag(name = "lesson", description = "Operaciones relacionadas con las lecciones")
public class LessonController {
    @Autowired
    private final ILessonService iLessonService;


    @GetMapping
    public ResponseEntity<Page<LessonResponse>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "8") int size,
            @RequestHeader(required = false) SortType sortType){

        if (Objects.isNull(sortType)){
            sortType = SortType.NONE;
        }

        return ResponseEntity.ok(this.iLessonService.getAll(page - 1, size, sortType));
    }



    @PostMapping
    public ResponseEntity<LessonResponse> create(@Validated @RequestBody LessonRequest lessonRequest){
        return ResponseEntity.ok(this.iLessonService.create(lessonRequest));
    }


    @GetMapping(path = "/{id}")
    public ResponseEntity<LessonResponse> get(@PathVariable Long id){
        return ResponseEntity.ok(this.iLessonService.get(id));
    }



    @PutMapping(path = "/{id}")
    public ResponseEntity<LessonResponse> update(@RequestBody UpdatedLessonRequest request, @PathVariable Long id){
        return ResponseEntity.ok(this.iLessonService.customUpdate(request, id));
    }











}
