package com.clomagno.inmobiliarias.rest.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.clomagno.inmobiliarias.rest.model.UnidadFuncional;

@RepositoryRestResource(collectionResourceRel = "unidadFuncional", path = "unidadFuncional")
public interface UnidadFuncionalRepository extends CrudRepository<UnidadFuncional, Long>
{
	
}
