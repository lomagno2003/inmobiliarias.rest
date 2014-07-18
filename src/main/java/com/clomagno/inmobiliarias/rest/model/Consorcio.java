package com.clomagno.inmobiliarias.rest.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.clomagno.inmobiliarias.rest.model.GastoOrdinario;
import com.clomagno.inmobiliarias.rest.model.CambioInteres;

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
	
	@OneToMany(cascade = {CascadeType.ALL})
	private List<CambioInteres> cambioInteres;
	
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

	public Collection<GastoOrdinario> getGastoOrdinario() {
	    return gastoOrdinario;
	}

	public void setGastoOrdinario(Collection<GastoOrdinario> param) {
	    this.gastoOrdinario = param;
	}

	public List<CambioInteres> getCambioInteres() {
	    return cambioInteres;
	}

	public void setCambioInteres(List<CambioInteres> param) {
	    this.cambioInteres = param;
	}
	
	/******************************************************************
	 ****************************NON-POJO******************************
	 ******************************************************************/

	public Long getId(){
		return getIdConsorcio();
	}
	
	public Double getInteresActual(){
		List<CambioInteres> cambiosIntereses = this.getCambioInteres();
		Collections.sort(cambiosIntereses, new IUbicableEnElTiempo.DateComparator());
		return cambiosIntereses.iterator().next().getInteres();
	}
	
	@JsonIgnore
	public void setInteresActual(Double interes){
		CambioInteres newCambioInteres = new CambioInteres();
		newCambioInteres.setFecha(Calendar.getInstance().getTime());
		newCambioInteres.setInteres(interes);
		
		this.getCambioInteres().add(newCambioInteres);
	}
}