package com.clomagno.inmobiliarias.rest.model;

import java.io.Serializable;

import javax.persistence.Entity;
import com.clomagno.inmobiliarias.rest.model.Consorcio;
import javax.persistence.ManyToOne;

@Entity
public class GastoOrdinario extends Gasto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ManyToOne(optional=false)
	private Consorcio consorcio;
	
	public Consorcio getConsorcio() {
	    return consorcio;
	}
	public void setConsorcio(Consorcio param) {
	    this.consorcio = param;
	}
}