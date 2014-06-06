package com.clomagno.inmobiliarias.rest.model;

import java.io.Serializable;

import javax.persistence.Entity;
import com.clomagno.inmobiliarias.rest.model.Propiedad;
import java.util.Collection;
import javax.persistence.OneToMany;

@Entity
public class Propietario extends Persona implements Serializable {

	private static final long serialVersionUID = 1L;
	@OneToMany(mappedBy = "propietario")
	private Collection<Propiedad> propiedad;

	public Propietario() {
	}

	public Collection<Propiedad> getPropiedad() {
		return propiedad;
	}

	public void setPropiedad(Collection<Propiedad> param) {
		this.propiedad = param;
	}

}