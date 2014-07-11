package com.clomagno.inmobiliarias.rest.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.clomagno.inmobiliarias.rest.model.Concepto;

@RepositoryRestResource(collectionResourceRel = "concepto", path = "concepto")
public interface ConceptoRepository extends CrudRepository<Concepto, Long>
{
	
}
