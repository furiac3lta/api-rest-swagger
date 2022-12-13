package com.gestion.gestion.inmobiliaria.services.impl;

import com.gestion.gestion.inmobiliaria.dto.InmuebleDto;
import com.gestion.gestion.inmobiliaria.dto.UserDto;
import com.gestion.gestion.inmobiliaria.entities.InmuebleEntity;
import com.gestion.gestion.inmobiliaria.entities.User;
import com.gestion.gestion.inmobiliaria.models.request.InmuebleModelRequest;
import com.gestion.gestion.inmobiliaria.models.response.InmuebleModelResponse;
import com.gestion.gestion.inmobiliaria.models.response.UserModelResponse;
import com.gestion.gestion.inmobiliaria.repositories.InmuebleRepository;
import com.gestion.gestion.inmobiliaria.repositories.UserRepository;
import com.gestion.gestion.inmobiliaria.services.InmuebleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class InmuebleServiceImpl implements InmuebleService {
    @Autowired
    private InmuebleRepository inmuebleRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    User user = new User();

    @Override
    @Transactional
    public InmuebleModelResponse save(InmuebleModelRequest inmuebleModelRequest) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> userOptional = userRepository.findByEmail(email);
        User user = userOptional.get();
        UserModelResponse userModelResponse = userEntityToResponse(user);
        InmuebleDto inmuebleDto = modelMapper.map(inmuebleModelRequest, InmuebleDto.class);
        InmuebleEntity inmuebleEntity = modelMapper.map(inmuebleDto, InmuebleEntity.class);
        inmuebleEntity.setInmuebleId(UUID.randomUUID().toString());
        inmuebleEntity.setCreateAt(new Date());
        inmuebleEntity.setUpdateAt(new Date());
        inmuebleEntity.setUser(user);
        inmuebleEntity = inmuebleRepository.save(inmuebleEntity);
        InmuebleDto inmuebleDtoResponse = modelMapper.map(inmuebleEntity, InmuebleDto.class);
        InmuebleModelResponse inmuebleModelResponse = modelMapper.map(inmuebleDtoResponse, InmuebleModelResponse.class);
        inmuebleModelResponse.setUserModelResponse(userModelResponse);
        return inmuebleModelResponse;
    }

    @Override
    @Transactional
    public InmuebleModelResponse update(InmuebleModelRequest inmuebleModelRequest, Long id) {
        try {
            UserModelResponse userModelResponse = userLoginToResponse();
            InmuebleEntity inmuebleEntity = inmuebleRequestToEntityMethod(inmuebleModelRequest);
            InmuebleEntity inmueble = inmuebleRepository.findById(id).get();
            inmueble.setUpdateAt(new Date());
            inmueble.setCiudad(inmuebleEntity.getCiudad());
            inmueble.setContacto(inmuebleEntity.getContacto());
            inmueble.setDireccion(inmuebleEntity.getDireccion());
            inmueble.setTelefono(inmuebleEntity.getTelefono());
            inmueble.setTipo(inmuebleEntity.getTipo());
            inmueble.setTitulo(inmuebleEntity.getTitulo());
            InmuebleEntity inmuebleUpdated = inmuebleRepository.save(inmueble);
            InmuebleModelResponse inmuebleModelResponse = inmuebleEntityToResponseMethod(inmuebleUpdated);
            inmuebleModelResponse.setUserModelResponse(userModelResponse);
            return inmuebleModelResponse;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional
    public List<InmuebleModelResponse> findAll() {
        return inmuebleRepository.findAll()
                .stream()
                .map(this::inmuebleEntityToResponseMethodWithUserResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public InmuebleModelResponse findById(Long id) {
        InmuebleEntity inmuebleEntity = inmuebleRepository.findById(id).get();
        InmuebleModelResponse inmuebleModelResponse = inmuebleEntityToResponseMethodWithUserResponse(inmuebleEntity);
        return inmuebleModelResponse;

    }

    @Override
    @Transactional
    public String delete(Long id) {
        try {
            InmuebleEntity inmuebleOptional = inmuebleRepository.findById(id).get();
            inmuebleOptional.setActivo(false);
            inmuebleRepository.save(inmuebleOptional);
            return "success delete";
        }catch (Exception e){
            e.printStackTrace();
            return "something wrong";
        }
    }

    //Metodo para pasar de entidad a response
    private InmuebleModelResponse inmuebleEntityToResponseMethod(InmuebleEntity inmuebleEntity){
        InmuebleDto inmuebleDto = modelMapper.map(inmuebleEntity, InmuebleDto.class);
        InmuebleModelResponse inmuebleModelResponse = modelMapper.map(inmuebleDto, InmuebleModelResponse.class);
        return inmuebleModelResponse;
    }
    //Metodo para pasar de request a entidad
    private InmuebleEntity inmuebleRequestToEntityMethod(InmuebleModelRequest inmuebleModelRequest){
        InmuebleDto inmuebleDto = modelMapper.map(inmuebleModelRequest, InmuebleDto.class);
        InmuebleEntity inmuebleEntity = modelMapper.map(inmuebleDto, InmuebleEntity.class);
        return inmuebleEntity;
    }
    //Medtodo para pasar de entidad a response
    private UserModelResponse userEntityToResponse(User user){
        UserDto userDto = modelMapper.map(user, UserDto.class);
        UserModelResponse userModelResponse = modelMapper.map(userDto, UserModelResponse.class);
        return userModelResponse;
    }

    //Metodo para pasar de entidad a response seteando userResponse
    private InmuebleModelResponse inmuebleEntityToResponseMethodWithUserResponse(InmuebleEntity inmuebleEntity){
        User user = inmuebleEntity.getUser();
        UserModelResponse userModelResponse = userEntityToResponse(user);
        InmuebleDto inmuebleDto = modelMapper.map(inmuebleEntity, InmuebleDto.class);
        InmuebleModelResponse inmuebleModelResponse = modelMapper.map(inmuebleDto, InmuebleModelResponse.class);
        inmuebleModelResponse.setUserModelResponse(userModelResponse);
        return inmuebleModelResponse;
    }
    private UserModelResponse userLoginToResponse(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> userOptional = userRepository.findByEmail(email);
        User user = userOptional.get();
        UserModelResponse userModelResponse = userEntityToResponse(user);
        return userModelResponse;
    }
}
