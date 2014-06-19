package com.clomagno.inmobiliarias.rest.resources.expensas;

public class Expensa {
	private Long monto;

	public Expensa(Long monto) {
		super();
		this.monto = monto;
	}

	public Long getMonto() {
		return monto;
	}

	public void setMonto(Long monto) {
		this.monto = monto;
	}
	
	
}
