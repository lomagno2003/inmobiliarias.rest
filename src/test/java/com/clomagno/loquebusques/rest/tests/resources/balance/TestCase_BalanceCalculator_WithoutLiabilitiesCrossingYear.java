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
 * but got a positive balance in previous month
 * @author clomagno
 *
 */
public class TestCase_BalanceCalculator_WithoutLiabilitiesCrossingYear extends TestCase_BalanceCalculator_Abstract {
	@Test
	public void testWithoutLiabilities() throws ParseException {
		List<GastoExtraordinario> gastosExtraordinarios = new LinkedList<GastoExtraordinario>();
		List<Pago> pagos = new LinkedList<Pago>();
		List<GastoOrdinario> gastosOrdinarios = new LinkedList<GastoOrdinario>();

		/**------------------------------------------------------------------------------------
		 * --------------------------------------Month 11/2013----------------------------------
		 * ------------------------------------------------------------------------------------
		 */
		//-------------------Generation of GastosExtraordinarios-------------------
		gastosExtraordinarios.add(generateGastoExtraordinario(11, 2013, 50.0));
		gastosExtraordinarios.add(generateGastoExtraordinario(11, 2013, 100.0));
		gastosExtraordinarios.add(generateGastoExtraordinario(11, 2013, 100.0));
		
		//-------------------Generation of Pagos-------------------
		pagos.add(generatePago(11, 2013, 450.0));

		//-------------------Generation of GastosOrdinarios-------------------
		gastosOrdinarios.add(generateGastoOrdinario(11, 2013, 100.0));
		gastosOrdinarios.add(generateGastoOrdinario(11, 2013, 100.0));
		
		//The balance until now should be 100 positive
		
		/**------------------------------------------------------------------------------------
		 * --------------------------------------Month 0/2014--------------------------------
		 * ------------------------------------------------------------------------------------
		 */
		//-------------------Generation of GastosExtraordinarios-------------------
		gastosExtraordinarios.add(generateGastoExtraordinario(0, 2014, 50.0));
		
		//-------------------Generation of Pagos-------------------
		pagos.add(generatePago(0, 2014, 120.0));

		//-------------------Generation of GastosOrdinarios-------------------
		gastosOrdinarios.add(generateGastoOrdinario(0, 2014, 100.0));
		
		//The balance until now should be 120 positive
		
		/**------------------------------------------------------------------------------------
		 * --------------------------------------Configuration of UnidadFuncional--------------
		 * ------------------------------------------------------------------------------------
		 */
		
		//Apply Gastos and Pagos to the UnidadFuncional
		unidadFuncional.setGastoExtraordinario(gastosExtraordinarios);
		unidadFuncional.setPago(pagos);
		unidadFuncional.getConsorcio().setGastoOrdinario(gastosOrdinarios);
		
		
		/**------------------------------------------------------------------------------------
		 * --------------------------------------Asserts---------------------------------------
		 * ------------------------------------------------------------------------------------
		 */
		Double balance;
		Calendar calendar;
		
		//Test the balance calculator at december of 2013(should be 100 positive)
		calendar= Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2013);
		calendar.set(Calendar.MONTH, 11);
		balance = balanceCalculator.getBalance(unidadFuncional, calendar.getTime());
		assertEquals("The balance is wrong",100.0, balance, 0.005);
		
		//Test the balance calculator at january of 2014(should be 120 positive)
		calendar= Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2014);
		calendar.set(Calendar.MONTH, 0);
		balance = balanceCalculator.getBalance(unidadFuncional, calendar.getTime());
		assertEquals("The balance is wrong",120.0, balance, 0.005);
	}
}
