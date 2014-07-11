package com.clomagno.inmobiliarias.rest.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.clomagno.inmobiliarias.rest.model.Consorcio;
import com.clomagno.inmobiliarias.rest.model.GastoOrdinario;

@RepositoryRestResource(collectionResourceRel = "gastoordinario", path = "gastoordinario")
public interface GastoOrdinarioRepository extends CrudRepository<GastoOrdinario, Long>
{
	public List<GastoOrdinario> findByConsorcioAndFechaBetween(Consorcio consorcio, Date fechaBegin, Date fechaEnd);
}
