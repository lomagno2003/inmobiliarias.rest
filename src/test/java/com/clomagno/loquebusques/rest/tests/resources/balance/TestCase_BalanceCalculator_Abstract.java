package com.clomagno.loquebusques.rest.tests.resources.balance;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.clomagno.inmobiliarias.rest.model.Consorcio;
import com.clomagno.inmobiliarias.rest.model.GastoExtraordinario;
import com.clomagno.inmobiliarias.rest.model.GastoOrdinario;
import com.clomagno.inmobiliarias.rest.model.Pago;
import com.clomagno.inmobiliarias.rest.model.UnidadFuncional;
import com.clomagno.inmobiliarias.rest.resources.balance.BalanceCalculatorLoadAll;
import com.clomagno.inmobiliarias.rest.resources.balance.IBalanceCalculator;

public class TestCase_BalanceCalculator_Abstract {
	protected UnidadFuncional unidadFuncional;
	protected IBalanceCalculator balanceCalculator;
	
	private Date getDate(Integer mes, Integer año){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, mes);
		calendar.set(Calendar.YEAR, año);
		calendar.set(Calendar.DAY_OF_MONTH,14);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		return calendar.getTime();
	}
	
	@Before
	public void setUp(){
		unidadFuncional = new UnidadFuncional();
		unidadFuncional.setPorcentajeGastosComunes(0.5);
		
		Consorcio consorcio = new Consorcio();
		unidadFuncional.setConsorcio(consorcio);
		
		balanceCalculator = new BalanceCalculatorLoadAll();
	}
	
	protected GastoExtraordinario generateGastoExtraordinario(Integer mes, Integer año, Double monto) throws ParseException{
		GastoExtraordinario result = new GastoExtraordinario();
		result.setUnidadFuncional(unidadFuncional);
		result.setMonto(monto);
		result.setFecha(getDate(mes,año));
		
		return result;
	}
	
	protected GastoOrdinario generateGastoOrdinario(Integer mes, Integer año, Double monto) throws ParseException{
		GastoOrdinario result = new GastoOrdinario();
		result.setConsorcio(unidadFuncional.getConsorcio());
		result.setMonto(monto);
		result.setFecha(getDate(mes,año));
		
		return result;
	}
	
	protected Pago generatePago(Integer mes, Integer año, Double monto) throws ParseException{
		Pago result = new Pago();
		result.setUnidadFuncional(unidadFuncional);
		result.setMonto(monto);
		result.setFecha(getDate(mes,año));
		
		return result;
	}
	
	
}
