package com.clomagno.inmobiliarias.rest.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.clomagno.inmobiliarias.rest.model.Gasto;

@RepositoryRestResource(collectionResourceRel = "gasto", path = "gasto")
public interface GastoRepository extends CrudRepository<Gasto, Long>
{
	
}
