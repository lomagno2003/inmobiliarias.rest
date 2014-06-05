package com.clomagno.inmobiliarias.rest.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.clomagno.inmobiliarias.rest.model.Gasto;

@RepositoryRestResource(collectionResourceRel = "gastos", path = "gastos")
public interface GastoRepository extends CrudRepository<Gasto, Long>
{
	
}
