package com.gestion.gestion.inmobiliaria.models.response;

import com.gestion.gestion.inmobiliaria.dto.UserDto;
import com.gestion.gestion.inmobiliaria.enums.Tipo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class InmuebleModelResponse {
    private String inmuebleId;
    private String titulo;
    private String direccion;
    private String ciudad;
    private Tipo tipo;
    private Boolean activo = true;
    private Date createAt;
    private Date updateAt;
    private UserModelResponse userModelResponse;

}
