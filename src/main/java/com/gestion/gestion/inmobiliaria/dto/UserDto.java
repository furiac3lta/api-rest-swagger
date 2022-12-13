package com.gestion.gestion.inmobiliaria.dto;

import com.gestion.gestion.inmobiliaria.entities.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class UserDto {

    private static final long serialVersionUID = 1L;

    private long id;
    private String name;
    private String username;
    private String email;
    private String password;
    private String contacto;
    private Long telefono;
    private Set<Role> roles;
    private List<InmuebleDto> inmuebleDtos;
}
