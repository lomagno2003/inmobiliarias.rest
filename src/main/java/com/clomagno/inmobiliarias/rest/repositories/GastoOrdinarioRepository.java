package com.clomagno.inmobiliarias.rest.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.clomagno.inmobiliarias.rest.model.Gasto;
import com.clomagno.inmobiliarias.rest.model.GastoOrdinario;

@RepositoryRestResource(collectionResourceRel = "gastoordinario", path = "gastoordinario")
public interface GastoOrdinarioRepository extends CrudRepository<GastoOrdinario, Long>
{
	
}
