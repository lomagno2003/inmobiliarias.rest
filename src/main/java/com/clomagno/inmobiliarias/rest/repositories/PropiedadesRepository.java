package com.clomagno.inmobiliarias.rest.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.clomagno.inmobiliarias.rest.model.Propiedad;

@RepositoryRestResource(collectionResourceRel = "propiedades", path = "propiedades")
public interface PropiedadesRepository extends CrudRepository<Propiedad, Long>
{
	
}
