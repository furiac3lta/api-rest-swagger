package com.gestion.gestion.inmobiliaria.controllers;

import com.gestion.gestion.inmobiliaria.entities.User;
import com.gestion.gestion.inmobiliaria.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    public ResponseEntity<?> getInmuebles (){
return null;
    }
}
