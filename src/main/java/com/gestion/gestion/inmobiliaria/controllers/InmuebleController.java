package com.gestion.gestion.inmobiliaria.controllers;

import com.gestion.gestion.inmobiliaria.dto.UserDto;
import com.gestion.gestion.inmobiliaria.entities.User;
import com.gestion.gestion.inmobiliaria.models.request.InmuebleModelRequest;
import com.gestion.gestion.inmobiliaria.models.response.InmuebleModelResponse;
import com.gestion.gestion.inmobiliaria.models.response.UserModelResponse;
import com.gestion.gestion.inmobiliaria.repositories.UserRepository;
import com.gestion.gestion.inmobiliaria.services.impl.InmuebleServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/inmuebles")
public class InmuebleController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private InmuebleServiceImpl inmuebleService;

    @Autowired
    private UserRepository userRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<InmuebleModelResponse> save (@RequestBody InmuebleModelRequest inmuebleModelRequest){
        InmuebleModelResponse inmuebleModelResponse = inmuebleService.save(inmuebleModelRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(inmuebleModelResponse);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<InmuebleModelResponse> update(@RequestBody InmuebleModelRequest inmuebleModelRequest, @PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(inmuebleService.update(inmuebleModelRequest, id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<InmuebleModelResponse>> findAll(){
        List<InmuebleModelResponse> inmuebleModelResponseList = inmuebleService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body((inmuebleModelResponseList));
      //  return new ResponseEntity(inmuebleModelResponseList, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<InmuebleModelResponse> find(@PathVariable("id") Long id){
        InmuebleModelResponse inmuebleModelResponse = inmuebleService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(inmuebleModelResponse);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(inmuebleService.delete(id)) ;
    }

}
