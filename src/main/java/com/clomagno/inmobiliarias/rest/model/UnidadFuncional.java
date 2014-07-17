package com.clomagno.inmobiliarias.rest.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonProperty;

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
	@OneToMany(mappedBy = "unidadFuncional")
	private Collection<GastoExtraordinario> gastoExtraordinario;
	@OneToMany(mappedBy = "unidadFuncional")
	private Collection<Pago> pago;
	@OneToMany
	private List<CambioPorcentajeGastos> cambioPorcentajeGastos;
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

	public List<CambioPorcentajeGastos> getCambioPorcentajeGastos() {
	    return cambioPorcentajeGastos;
	}

	public void setCambioPorcentajeGastos(List<CambioPorcentajeGastos> param) {
	    this.cambioPorcentajeGastos = param;
	}
	
	@JsonProperty
	public Double getPorcentajeGastosActual(){
		List<CambioPorcentajeGastos> cambiosIntereses = this.getCambioPorcentajeGastos();
		Collections.sort(cambiosIntereses, new IUbicableEnElTiempo.DateComparator());
		return cambiosIntereses.iterator().next().getPorcentajeGasto();
	}
	
	@JsonProperty
	public void setPorcentajeGastosActual(Double porcentaje){
		CambioPorcentajeGastos newCambioPorcentajeGastos = new CambioPorcentajeGastos();
		newCambioPorcentajeGastos.setFecha(Calendar.getInstance().getTime());
		newCambioPorcentajeGastos.setPorcentajeGasto(porcentaje);
		
		this.getCambioPorcentajeGastos().add(newCambioPorcentajeGastos);
	}
}