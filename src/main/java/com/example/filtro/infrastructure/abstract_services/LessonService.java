package com.example.filtro.infrastructure.abstract_services;

import com.example.filtro.api.dto.request.used_request.LessonRequest;
import com.example.filtro.api.dto.response.used_response.LessonResponse;

public interface LessonService extends CrudService<LessonRequest, LessonResponse, Long> {
}
