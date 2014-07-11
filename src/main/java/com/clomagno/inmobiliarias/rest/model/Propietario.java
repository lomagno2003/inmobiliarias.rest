package com.clomagno.inmobiliarias.rest.model;

import java.io.Serializable;

import javax.persistence.Entity;
import com.clomagno.inmobiliarias.rest.model.UnidadFuncional;
import java.util.Collection;
import javax.persistence.OneToMany;

@Entity
public class Propietario extends Persona implements Serializable {

	private static final long serialVersionUID = 1L;
	@OneToMany(mappedBy = "propietario")
	private Collection<UnidadFuncional> unidadFuncional;

	public Propietario() {
	}

	public Collection<UnidadFuncional> getUnidadFuncional() {
		return unidadFuncional;
	}

	public void setUnidadFuncional(Collection<UnidadFuncional> param) {
		this.unidadFuncional = param;
	}

}