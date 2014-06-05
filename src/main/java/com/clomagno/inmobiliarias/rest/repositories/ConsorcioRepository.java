package com.clomagno.inmobiliarias.rest.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.clomagno.inmobiliarias.rest.model.Consorcio;;

@RepositoryRestResource(collectionResourceRel = "consorcios", path = "consorcios")
public interface ConsorcioRepository extends CrudRepository<Consorcio, Long>
{
	
}
