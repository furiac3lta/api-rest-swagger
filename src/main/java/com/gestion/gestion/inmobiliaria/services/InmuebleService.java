package com.gestion.gestion.inmobiliaria.services;



import com.gestion.gestion.inmobiliaria.models.request.InmuebleModelRequest;
import com.gestion.gestion.inmobiliaria.models.response.InmuebleModelResponse;

import java.util.List;

public interface InmuebleService {
    public InmuebleModelResponse save(InmuebleModelRequest inmuebleModelRequest);
    public InmuebleModelResponse update(InmuebleModelRequest inmuebinmuebleModelRequestle, Long id);
    public List<InmuebleModelResponse> findAll();
    public InmuebleModelResponse findById(Long id);
    public String delete(Long id);
}