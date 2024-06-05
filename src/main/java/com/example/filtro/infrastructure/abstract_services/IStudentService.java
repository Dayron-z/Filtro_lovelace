package com.example.filtro.infrastructure.abstract_services;

import com.example.filtro.api.dto.request.custom_request.UpdatedStudentRequest;
import com.example.filtro.api.dto.request.used_request.StudentRequest;
import com.example.filtro.api.dto.response.used_response.StudentResponse;

public interface IStudentService extends CrudService<StudentRequest, StudentResponse, Long>{
    StudentResponse inhabilitar(Long id);
    StudentResponse customUpdate(UpdatedStudentRequest request, Long id);
}
