package com.clomagno.inmobiliarias.rest.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.clomagno.inmobiliarias.rest.model.GastoOrdinario;

@Entity
public class Consorcio implements Serializable {

	private static final long serialVersionUID = 1L;

	public Consorcio() {
	}

	@Id
	@GenericGenerator(name="gen",strategy="increment")
	@GeneratedValue(generator="gen")
	private long idConsorcio;
	@OneToMany(mappedBy = "consorcio")
	private Collection<UnidadFuncional> unidadFuncional;
	private String nombre;
	@OneToMany(mappedBy = "consorcio")
	private Collection<GastoOrdinario> gastoOrdinario;
	public long getIdConsorcio() {
		return idConsorcio;
	}

	public void setIdConsorcio(long id) {
		this.idConsorcio = id;
	}

	public Collection<UnidadFuncional> getUnidadFuncional() {
		return unidadFuncional;
	}

	public void setUnidadFuncional(Collection<UnidadFuncional> param) {
		this.unidadFuncional = param;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String param) {
		this.nombre = param;
	}

	@JsonProperty("id")
	public Long getId(){
		return getIdConsorcio();
	}

	public Collection<GastoOrdinario> getGastoOrdinario() {
	    return gastoOrdinario;
	}

	public void setGastoOrdinario(Collection<GastoOrdinario> param) {
	    this.gastoOrdinario = param;
	}
}