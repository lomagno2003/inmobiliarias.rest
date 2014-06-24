package com.clomagno.inmobiliarias.rest.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.clomagno.inmobiliarias.rest.model.TipoGasto;

@RepositoryRestResource(collectionResourceRel = "tipoGasto", path = "tipoGasto")
public interface TipoGastoRepository extends CrudRepository<TipoGasto, Long>
{
	
}
