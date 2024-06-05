package com.example.filtro.infrastructure.abstract_services;

import com.example.filtro.api.dto.request.used_request.ClassRequest;
import com.example.filtro.api.dto.request.used_request.MultimediaRequest;
import com.example.filtro.api.dto.response.used_response.ClassResponse;
import com.example.filtro.api.dto.response.used_response.MultimediaResponse;

public interface MultimediaService extends CrudService<MultimediaRequest, MultimediaResponse, Long>{
}
