package com.example.filtro.infrastructure.services;


import com.example.filtro.api.dto.request.custom_request.UpdatedStudentRequest;
import com.example.filtro.api.dto.request.used_request.StudentRequest;
import com.example.filtro.api.dto.response.custom_response.ClassResponseToStudentResponse;
import com.example.filtro.api.dto.response.custom_response.LessonResponseToBasicClassResponse;
import com.example.filtro.api.dto.response.custom_response.LessonResponseToClassResponse;
import com.example.filtro.api.dto.response.custom_response.MultimediaResponseToLessonResponse;
import com.example.filtro.api.dto.response.used_response.StudentResponse;
import com.example.filtro.domain.entities.ClassEntity;
import com.example.filtro.domain.entities.Lesson;
import com.example.filtro.domain.entities.Student;
import com.example.filtro.domain.repositories.ClassRepository;
import com.example.filtro.domain.repositories.StudentRepository;
import com.example.filtro.infrastructure.abstract_services.IClassService;
import com.example.filtro.infrastructure.abstract_services.IStudentService;
import com.example.filtro.util.enums.SortType;
import com.example.filtro.util.exception.BadRequestException;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentService implements IStudentService {


    @Autowired
    private final StudentRepository studentRepository;
    @Autowired
    private final ClassRepository classRepository;

    @Override
    public StudentResponse create(StudentRequest request) {
        Student student = requestToEntity(request);
        ClassEntity classEntity = this.classRepository.findById(request.getClass_id()).orElseThrow(()-> new BadRequestException("No hay clases con ese id"));

        student.setAClass(classEntity);
        student.setCreated_at(LocalDateTime.now());
        student.setActive(true);

        return this.entityToResponse(this.studentRepository.save(student));
    }

    @Override
    public StudentResponse get(Long id) {
        return this.entityToResponse(this.findById(id));
    }

    @Override
    public StudentResponse update(StudentRequest request, Long id) {

        return null;
    }


    public StudentResponse customUpdate(UpdatedStudentRequest request, Long id){
        Student student = this.findById(id);
        Student updatedStudent = this.requestToEntity(request);

        updatedStudent.setId(id);
        updatedStudent.setAClass(student.getAClass());
        updatedStudent.setCreated_at(student.getCreated_at());
        updatedStudent.setActive(student.getActive());


        return this.entityToResponse(this.studentRepository.save(updatedStudent));
    }


    public StudentResponse inhabilitar(Long id){
        Student student = this.findById(id);
        student.setActive(false);
        return this.entityToResponse(this.findById(id));
    }


    private Student requestToEntity(UpdatedStudentRequest studentRequest){
        return Student.builder()
                .name(studentRequest.getName())
                .email(studentRequest.getEmail())
                .build();
    }
    @Override
    public void delete(Long aLong) {

    }

    @Override
    public Page<StudentResponse> getAll(int page, int size, SortType sort) {
        if (page < 0){
            page = 0;
        }

        PageRequest pagination = null;

        switch (sort){
            case NONE -> pagination = PageRequest.of(page, size);
            case ASC -> pagination = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC));
            case DESC -> pagination = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC));
        }


        return this.studentRepository.findAll(pagination).map(student -> this.entityToResponse(student));


    }

    private Student requestToEntity(StudentRequest studentRequest){
        return Student.builder()
                .name(studentRequest.getName())
                .email(studentRequest.getEmail())
                .build();
    }
    private StudentResponse entityToResponse(Student student){

        ClassResponseToStudentResponse aClass =  this.entityToClassResponse(student.getAClass());

       return StudentResponse.builder()
                .id(student.getId())
                .name(student.getName())
                .email(student.getEmail())
                .created_at(student.getCreated_at())
                .active(student.getActive())
                .aClass(aClass)
                .build();
    }
    private ClassResponseToStudentResponse entityToClassResponse(ClassEntity classEntity){

        List<LessonResponseToBasicClassResponse> lessons = classEntity.getLessons().stream().map(lesson -> this.entityToLessonResponse(lesson)).collect(Collectors.toList());


        return  ClassResponseToStudentResponse.builder()
                .id(classEntity.getId())
                .name(classEntity.getName())
                .description(classEntity.getDescription())
                .created_at(classEntity.getCreated_at())
                .active(classEntity.getActive())
                .lessons(lessons)
                .build();
    }
    private LessonResponseToBasicClassResponse entityToLessonResponse(Lesson lesson){
        return  LessonResponseToBasicClassResponse.builder()
                .id(lesson.getId())
                .title(lesson.getTitle())
                .content(lesson.getContent())
                .created_at(lesson.getCreated_at())
                .active(lesson.getActive())
                .build();
    }
    private Student findById(Long id){
        return this.studentRepository.findById(id).orElseThrow(()->  new BadRequestException("No hay estudiantes con este id"));
    }





}
