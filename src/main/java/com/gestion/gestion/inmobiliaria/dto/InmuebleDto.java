package com.gestion.gestion.inmobiliaria.dto;

import com.gestion.gestion.inmobiliaria.entities.User;
import com.gestion.gestion.inmobiliaria.enums.Tipo;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class InmuebleDto {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String inmuebleId;
    private String titulo;
    private String direccion;
    private String ciudad;
    private String contacto;
    private Long telefono;
    private Tipo tipo;
    private Boolean activo = true;
    private Date createAt;
    private Date updateAt;
    private User user;



}
