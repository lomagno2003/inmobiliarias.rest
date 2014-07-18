package com.clomagno.inmobiliarias.rest.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.clomagno.inmobiliarias.rest.resources.balance.BalanceCalculatorLoadAll;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "UnidadFuncional")
public class UnidadFuncional implements Serializable {
	private static final long serialVersionUID = 1L;

	public UnidadFuncional() {
		this.cambioPorcentajeGastos = new LinkedList<CambioPorcentajeGastos>();
	}

	@Id
	@GenericGenerator(name = "gen", strategy = "increment")
	@GeneratedValue(generator = "gen")
	private long idUnidadFuncional;
	
	@ManyToOne(optional=false)
	private Consorcio consorcio;

	@Column(nullable=false)
	private String nombre;
	
	private String direccion;
	
	@ManyToOne
	private Propietario propietario;
	
	@OneToMany(mappedBy = "unidadFuncional")
	private Collection<GastoExtraordinario> gastoExtraordinario;
	
	@OneToMany(mappedBy = "unidadFuncional")
	private Collection<Pago> pago;
	
	@OneToMany(cascade={CascadeType.ALL})
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
	
	/******************************************************************
	 ****************************NON-POJO******************************
	 ******************************************************************/
	
	public Long getId() {
		return getIdUnidadFuncional();
	}
	
	public Double getPorcentajeGastosActual(){
		List<CambioPorcentajeGastos> cambiosIntereses = this.getCambioPorcentajeGastos();
		if(cambiosIntereses.isEmpty()){
			return null;
		} else {
			Collections.sort(cambiosIntereses, new IUbicableEnElTiempo.DateComparator());
			return cambiosIntereses.iterator().next().getPorcentajeGasto();
		}
	}
	
	public Double getBalance(){
		Double balance = new BalanceCalculatorLoadAll().getBalance(this, Calendar.getInstance().getTime());
		return balance;
	}
	
	@JsonIgnore
	public void setPorcentajeGastosActual(Double porcentajeGastosActual){
		CambioPorcentajeGastos newCambioPorcentajeGastos = new CambioPorcentajeGastos();
		newCambioPorcentajeGastos.setFecha(Calendar.getInstance().getTime());
		newCambioPorcentajeGastos.setPorcentajeGasto(porcentajeGastosActual);
				
		this.getCambioPorcentajeGastos().add(newCambioPorcentajeGastos);
	}
}