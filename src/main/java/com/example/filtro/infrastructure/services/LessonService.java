package com.example.filtro.infrastructure.services;


import com.example.filtro.api.dto.request.custom_request.UpdatedLessonRequest;
import com.example.filtro.api.dto.request.used_request.LessonRequest;
import com.example.filtro.api.dto.response.custom_response.ClassResponseToLessonResponse;
import com.example.filtro.api.dto.response.custom_response.LessonResponseToClassResponse;
import com.example.filtro.api.dto.response.custom_response.MultimediaResponseToLessonResponse;
import com.example.filtro.api.dto.response.used_response.LessonResponse;
import com.example.filtro.domain.entities.ClassEntity;
import com.example.filtro.domain.entities.Lesson;
import com.example.filtro.domain.entities.Multimedia;
import com.example.filtro.domain.entities.Student;
import com.example.filtro.domain.repositories.ClassRepository;
import com.example.filtro.domain.repositories.LessonRepository;
import com.example.filtro.infrastructure.abstract_services.ILessonService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LessonService implements ILessonService {

    @Autowired
    private final LessonRepository lessonRepository;

    @Autowired
    private final ClassRepository classRepository;




    @Override
    public LessonResponse create(LessonRequest request) {
        Lesson lesson = this.requestToEntity(request);

        ClassEntity classEntity = this.classRepository.findById(request.getClass_id()).orElseThrow(()-> new BadRequestException("No hay clases con ese id"));




        lesson.setCreated_at(LocalDateTime.now());
        lesson.setActive(true);
        lesson.setAClass(classEntity);
        lesson.setMultimediaList(new ArrayList<>());


        return this.entityToResponse(this.lessonRepository.save(lesson));
    }

    @Override
    public LessonResponse get(Long id) {
        return this.entityToResponse(this.findById(id));
    }

    @Override
    public LessonResponse update(LessonRequest request, Long aLong) {
        return null;
    }



    public LessonResponse customUpdate(UpdatedLessonRequest request, Long id) {
        Lesson lesson = this.findById(id);
        Lesson updatedLesson = this.requestToEntity(request);

        updatedLesson.setId(id);
        updatedLesson.setCreated_at(lesson.getCreated_at());
        updatedLesson.setActive(lesson.getActive());
        updatedLesson.setMultimediaList(lesson.getMultimediaList());

        return this.entityToResponse(this.lessonRepository.save(updatedLesson));
    }


    @Override
    public void delete(Long aLong) {
    }

    @Override
    public Page<LessonResponse> getAll(int page, int size, SortType sort) {
        if (page < 0){
            page = 0;
        }

        PageRequest pagination = null;

        switch (sort){
            case NONE -> pagination = PageRequest.of(page, size);
            case ASC -> pagination = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC));
            case DESC -> pagination = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC));
        }


        return this.lessonRepository.findAll(pagination).map(classEntity -> this.entityToResponse(classEntity));
    }
    private Lesson requestToEntity(UpdatedLessonRequest request){
        return Lesson.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .build();

    }
    private Lesson requestToEntity(LessonRequest request){
        return Lesson.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .build();

    }

    private LessonResponse entityToResponse(Lesson lesson){
        ClassResponseToLessonResponse aClass = new ClassResponseToLessonResponse();
        BeanUtils.copyProperties(lesson.getAClass(),aClass);

        List<MultimediaResponseToLessonResponse> multimediaList = lesson.getMultimediaList().stream().map(multimedia -> this.entityToMultimediaResponse(multimedia)).collect(Collectors.toList());

        return LessonResponse.builder()
                .id(lesson.getId())
                .title(lesson.getTitle())
                .content(lesson.getContent())
                .active(lesson.getActive())
                .aClass(aClass)
                .multimediaList(multimediaList)
                .build();
    }


    private MultimediaResponseToLessonResponse entityToMultimediaResponse(Multimedia multimedia){
        return  MultimediaResponseToLessonResponse.builder()
                .id(multimedia.getId())
                .type(multimedia.getType())
                .url(multimedia.getUrl())
                .created_at(multimedia.getCreated_at())
                .active(multimedia.getActive())
                .build();
    }

    private Lesson findById(Long id){
        return this.lessonRepository.findById(id).orElseThrow(()-> new BadRequestException("No se encontró lesson con este id"));
    }


}
