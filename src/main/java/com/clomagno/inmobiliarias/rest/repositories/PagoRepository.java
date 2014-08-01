package com.clomagno.inmobiliarias.rest.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.clomagno.inmobiliarias.rest.model.Pago;
import com.clomagno.inmobiliarias.rest.model.UnidadFuncional;

@RepositoryRestResource(collectionResourceRel = "pago", path = "pago")
public interface PagoRepository extends CrudRepository<Pago, Long>
{
	public List<Pago> findByUnidadFuncionalAndFechaBetween(UnidadFuncional unidadFuncional, Date fechaBegin, Date fechaEnd);
}
