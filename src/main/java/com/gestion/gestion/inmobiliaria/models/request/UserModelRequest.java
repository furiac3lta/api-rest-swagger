package com.gestion.gestion.inmobiliaria.models.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserModelRequest {
    private String name;
    private String username;
    private String email;
    private String password;
    private String contacto;
    private Long telefono;
}
