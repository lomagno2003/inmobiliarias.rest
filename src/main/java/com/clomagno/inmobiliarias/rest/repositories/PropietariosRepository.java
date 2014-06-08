package com.clomagno.inmobiliarias.rest.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.clomagno.inmobiliarias.rest.model.Propietario;

@RepositoryRestResource(collectionResourceRel = "propietario", path = "propietario")
public interface PropietariosRepository extends CrudRepository<Propietario, Long>
{
	
}
