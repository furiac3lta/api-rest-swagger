package com.gestion.gestion.inmobiliaria.models.request;

import com.gestion.gestion.inmobiliaria.entities.User;
import com.gestion.gestion.inmobiliaria.enums.Tipo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InmuebleModelRequest {
    private String titulo;
    private String direccion;
    private String ciudad;
    private Tipo tipo;
    private User user;

}
