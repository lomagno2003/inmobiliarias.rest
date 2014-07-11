package com.clomagno.inmobiliarias.rest.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.clomagno.inmobiliarias.rest.model.Pago;

@RepositoryRestResource(collectionResourceRel = "pago", path = "pago")
public interface PagoRepository extends CrudRepository<Pago, Long>
{
	
}
