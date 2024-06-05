package com.example.filtro.infrastructure.abstract_services;

import com.example.filtro.api.dto.request.used_request.ClassRequest;
import com.example.filtro.api.dto.request.used_request.StudentRequest;
import com.example.filtro.api.dto.response.used_response.ClassResponse;
import com.example.filtro.api.dto.response.used_response.StudentResponse;

public interface StudentService extends CrudService<StudentRequest, StudentResponse, Long>{
}
