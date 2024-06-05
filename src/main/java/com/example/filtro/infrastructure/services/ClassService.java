package com.example.filtro.infrastructure.services;

import com.example.filtro.api.dto.request.used_request.ClassRequest;
import com.example.filtro.api.dto.response.custom_response.LessonResponseToClassResponse;
import com.example.filtro.api.dto.response.custom_response.MultimediaResponseToLessonResponse;
import com.example.filtro.api.dto.response.custom_response.StudentResponseToClassResponse;
import com.example.filtro.api.dto.response.used_response.ClassResponse;
import com.example.filtro.domain.entities.ClassEntity;
import com.example.filtro.domain.entities.Lesson;
import com.example.filtro.domain.entities.Multimedia;
import com.example.filtro.domain.entities.Student;
import com.example.filtro.domain.repositories.ClassRepository;
import com.example.filtro.infrastructure.abstract_services.IClassService;
import com.example.filtro.util.enums.SortType;
import com.example.filtro.util.exception.BadRequestException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class ClassService implements IClassService {


    @Autowired
    private final ClassRepository classRepository;

    @Override
    public ClassResponse create(ClassRequest request) {
        ClassEntity classEntity = this.requestToEntity(request);
        classEntity.setCreated_at(LocalDateTime.now());
        classEntity.setActive(true);
        classEntity.setStudents(new ArrayList<>());
        classEntity.setLessons(new ArrayList<>());

        return this.entityToResponse(this.classRepository.save(classEntity));
    }

    @Override
    public ClassResponse get(Long id) {
        return this.entityToResponse(this.findById(id));
    }

    @Override
    public ClassResponse update(ClassRequest request, Long aLong) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public Page<ClassResponse> getAll(int page, int size, SortType sort) {
        if (page < 0){
            page = 0;
        }

        PageRequest pagination = null;

        switch (sort){
            case NONE -> pagination = PageRequest.of(page, size);
            case ASC -> pagination = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC));
            case DESC -> pagination = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC));
        }


        return this.classRepository.findAll(pagination).map(classEntity -> this.entityToResponse(classEntity));
    }

    private ClassEntity requestToEntity(ClassRequest classRequest){
        return ClassEntity.builder()
                .name(classRequest.getName())
                .description(classRequest.getDescription())
                .build();
    }
    private ClassResponse entityToResponse(ClassEntity classEntity){
        List<LessonResponseToClassResponse> lesspns = classEntity.getLessons().stream().map(lesson -> this.entityToLessonResponse(lesson)).collect(Collectors.toList());

        List<StudentResponseToClassResponse> students = classEntity.getStudents().stream().map(student -> this.entityToStudentResponse(student)).collect(Collectors.toList());


        return ClassResponse.builder()
                .id(classEntity.getId())
                .name(classEntity.getName())
                .description(classEntity.getDescription())
                .created_at(classEntity.getCreated_at())
                .active(classEntity.getActive())
                .lessons(lesspns)
                .students(students)
                .build();
    }
    private StudentResponseToClassResponse entityToStudentResponse (Student student){
        return StudentResponseToClassResponse.builder()
                .id(student.getId())
                .name(student.getName())
                .email(student.getEmail())
                .created_at(student.getCreated_at())
                .build();

    }
    private LessonResponseToClassResponse entityToLessonResponse (Lesson lesson){
        List<MultimediaResponseToLessonResponse> multimedia = lesson.getMultimediaList().stream().map(multimedia1 -> this.entityToMultimediaResponse(multimedia1)).collect(Collectors.toList());


        return LessonResponseToClassResponse.builder()
                .id(lesson.getId())
                .title(lesson.getTitle())
                .content(lesson.getContent())
                .created_at(lesson.getCreated_at())
                .active(lesson.getActive())
                .multimediaList(multimedia)
                .build();
    }
    private MultimediaResponseToLessonResponse entityToMultimediaResponse(Multimedia multimedia){
        return MultimediaResponseToLessonResponse.builder()
                .id(multimedia.getId())
                .type(multimedia.getType())
                .url(multimedia.getUrl())
                .created_at(multimedia.getCreated_at())
                .active(multimedia.getActive())
                .build();
    }
    private ClassEntity findById(Long id){
        return this.classRepository.findById(id).orElseThrow(()-> new BadRequestException("No hay clase con ese id"));
    }


}
