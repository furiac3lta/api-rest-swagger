package com.gestion.gestion.inmobiliaria.repositories;

import com.gestion.gestion.inmobiliaria.entities.InmuebleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InmuebleRepository extends JpaRepository<InmuebleEntity, Long> {

}
