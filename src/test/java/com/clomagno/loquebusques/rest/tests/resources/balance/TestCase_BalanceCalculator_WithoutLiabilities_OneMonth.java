package com.clomagno.loquebusques.rest.tests.resources.balance;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.clomagno.inmobiliarias.rest.model.GastoExtraordinario;
import com.clomagno.inmobiliarias.rest.model.GastoOrdinario;
import com.clomagno.inmobiliarias.rest.model.Pago;

/**
 * In this testcase, the balance calculator should calculate well
 * the Balance of a UnidadFuncional in which it doesn't have liabilities
 * @author clomagno
 *
 */
public class TestCase_BalanceCalculator_WithoutLiabilities_OneMonth extends TestCase_BalanceCalculator_Abstract {
	@Test
	public void testWithoutLiabilities_OneMonth() throws ParseException {
		//Generation of GastosExtraordinarios
		List<GastoExtraordinario> gastosExtraordinarios = new LinkedList<GastoExtraordinario>();
		gastosExtraordinarios.add(generateGastoExtraordinario(02, 2014, 50.0));
		gastosExtraordinarios.add(generateGastoExtraordinario(02, 2014, 100.0));
		gastosExtraordinarios.add(generateGastoExtraordinario(02, 2014, 100.0));
		
		//Generation of Pagos
		List<Pago> pagos = new LinkedList<Pago>();
		pagos.add(generatePago(02, 2014, 450.0));

		//Generation of GastosOrdinarios
		List<GastoOrdinario> gastosOrdinarios = new LinkedList<GastoOrdinario>();
		gastosOrdinarios.add(generateGastoOrdinario(02, 2014, 100.0));
		gastosOrdinarios.add(generateGastoOrdinario(02, 2014, 100.0));
		
		//Apply Gastos and Pagos to the UnidadFuncional
		unidadFuncional.setGastoExtraordinario(gastosExtraordinarios);
		unidadFuncional.setPago(pagos);
		unidadFuncional.getConsorcio().setGastoOrdinario(gastosOrdinarios);
		
		Calendar calendar;
		
		//Test the balance calculator
		calendar= Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2014);
		calendar.set(Calendar.MONTH, 02);
		Double balance = balanceCalculator.getBalance(unidadFuncional, calendar.getTime());
		assertEquals("The balance is wrong",100.0, balance, 0.005);
	}
}
