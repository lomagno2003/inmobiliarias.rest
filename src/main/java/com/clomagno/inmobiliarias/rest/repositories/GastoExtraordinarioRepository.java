package com.clomagno.inmobiliarias.rest.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.clomagno.inmobiliarias.rest.model.GastoExtraordinario;
import com.clomagno.inmobiliarias.rest.model.UnidadFuncional;

@RepositoryRestResource(collectionResourceRel = "gastoExtraordinario", path = "gastoExtraordinario")
public interface GastoExtraordinarioRepository extends CrudRepository<GastoExtraordinario, Long>
{
	public List<GastoExtraordinario> findByUnidadFuncionalAndFechaBetween(UnidadFuncional unidadFuncional, Date fechaBegin, Date fechaEnd);
}
