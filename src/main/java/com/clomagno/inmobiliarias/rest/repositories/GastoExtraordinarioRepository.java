package com.clomagno.inmobiliarias.rest.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.clomagno.inmobiliarias.rest.model.GastoExtraordinario;

@RepositoryRestResource(collectionResourceRel = "gastoextraordinario", path = "gastoextraordinario")
public interface GastoExtraordinarioRepository extends CrudRepository<GastoExtraordinario, Long>
{
	
}
