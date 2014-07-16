package com.clomagno.inmobiliarias.rest.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.clomagno.inmobiliarias.rest.model.GastoExtraordinario;
import java.util.Collection;
import javax.persistence.OneToMany;
import com.clomagno.inmobiliarias.rest.model.Pago;

@Entity
@Table(name = "UnidadFuncional")
public class UnidadFuncional implements Serializable {

	private static final long serialVersionUID = 1L;

	public UnidadFuncional() {
	}

	@Id
	@GenericGenerator(name = "gen", strategy = "increment")
	@GeneratedValue(generator = "gen")
	private long idUnidadFuncional;
	@ManyToOne
	private Consorcio consorcio;
	private String nombre;
	private String direccion;
	@ManyToOne
	private Propietario propietario;
	private Double porcentajeGastosComunes;
	@OneToMany(mappedBy = "unidadFuncional")
	private Collection<GastoExtraordinario> gastoExtraordinario;
	@OneToMany(mappedBy = "unidadFuncional")
	private Collection<Pago> pago;
	public long getIdUnidadFuncional() {
		return idUnidadFuncional;
	}

	public void setIdUnidadFuncional(long id) {
		this.idUnidadFuncional = id;
	}

	public Consorcio getConsorcio() {
		return consorcio;
	}

	public void setConsorcio(Consorcio param) {
		this.consorcio = param;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String param) {
		this.nombre = param;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String param) {
		this.direccion = param;
	}

	public Propietario getPropietario() {
		return propietario;
	}

	public void setPropietario(Propietario param) {
		this.propietario = param;
	}

	public Double getPorcentajeGastosComunes() {
		return porcentajeGastosComunes;
	}

	public void setPorcentajeGastosComunes(Double param) {
		this.porcentajeGastosComunes = param;
	}

	@JsonProperty("id")
	public Long getId() {
		return getIdUnidadFuncional();
	}

	public Collection<GastoExtraordinario> getGastoExtraordinario() {
	    return gastoExtraordinario;
	}

	public void setGastoExtraordinario(Collection<GastoExtraordinario> param) {
	    this.gastoExtraordinario = param;
	}

	public Collection<Pago> getPago() {
	    return pago;
	}

	public void setPago(Collection<Pago> param) {
	    this.pago = param;
	}
}