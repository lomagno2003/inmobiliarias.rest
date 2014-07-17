package com.clomagno.loquebusques.rest.tests.resources.balance;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.clomagno.inmobiliarias.rest.model.CambioInteres;
import com.clomagno.inmobiliarias.rest.model.GastoExtraordinario;
import com.clomagno.inmobiliarias.rest.model.GastoOrdinario;
import com.clomagno.inmobiliarias.rest.model.Pago;

/**
 * In this testcase, the balance calculator should calculate well
 * the Balance of a UnidadFuncional in which it doesn't have liabilities
 * @author clomagno
 *
 */
public class TestCase_BalanceCalculator_WithLiabilities_InteresesChange extends TestCase_BalanceCalculator_Abstract {
	@Test
	public void testWithLiabilities_InteresesChange() throws ParseException {
		List<GastoExtraordinario> gastosExtraordinarios = new LinkedList<GastoExtraordinario>();
		List<Pago> pagos = new LinkedList<Pago>();
		List<GastoOrdinario> gastosOrdinarios = new LinkedList<GastoOrdinario>();

		/**------------------------------------------------------------------------------------
		 * --------------------------------------Month 0/2014----------------------------------
		 * ------------------------------------------------------------------------------------
		 */
		//-------------------Generation of GastosExtraordinarios-------------------
		gastosExtraordinarios.add(generateGastoExtraordinario(0, 2014, 50.0));
		gastosExtraordinarios.add(generateGastoExtraordinario(0, 2014, 100.0));
		gastosExtraordinarios.add(generateGastoExtraordinario(0, 2014, 100.0));
		
		//-------------------Generation of Pagos-------------------
		pagos.add(generatePago(0, 2014, 300.0));

		//-------------------Generation of GastosOrdinarios-------------------
		gastosOrdinarios.add(generateGastoOrdinario(0, 2014, 100.0));
		gastosOrdinarios.add(generateGastoOrdinario(0, 2014, 100.0));
		
		//The balance until now should be 50 negative
		
		/**------------------------------------------------------------------------------------
		 * --------------------------------------Month 1/2014--------------------------------
		 * ------------------------------------------------------------------------------------
		 */
		//-------------------Generation of GastosExtraordinarios-------------------
		gastosExtraordinarios.add(generateGastoExtraordinario(1, 2014, 50.0));
		
		//-------------------Generation of Pagos-------------------
		pagos.add(generatePago(1, 2014, 100.0));

		//-------------------Generation of GastosOrdinarios-------------------
		gastosOrdinarios.add(generateGastoOrdinario(1, 2014, 100.0));
		
		//The balance until now should be 0 -50 (-50*Taxes)
		
		//The Interes is changed in this month, so them are applied on the next
		List<CambioInteres> cambiosInteres = unidadFuncional.getConsorcio().getCambioInteres();
		CambioInteres newCambioInteres = new CambioInteres();
		newCambioInteres.setInteres(1.0);
		newCambioInteres.setFecha(getDate(1,2014));
		cambiosInteres.add(newCambioInteres);
		
		/**------------------------------------------------------------------------------------
		 * --------------------------------------Month 2/2014--------------------------------
		 * ------------------------------------------------------------------------------------
		 */		
		//-------------------Generation of GastosExtraordinarios-------------------
		gastosExtraordinarios.add(generateGastoExtraordinario(2, 2014, 100.0));
		
		//-------------------Generation of Pagos-------------------
		pagos.add(generatePago(2, 2014, 200.0));

		//-------------------Generation of GastosOrdinarios-------------------
		gastosOrdinarios.add(generateGastoOrdinario(2, 2014, 200.0));
		
		//The balance until now should be 0 + (-50*Taxes) + ((-50*Taxes)*Taxes)
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
		Double taxes1 = 0.5;
		Double taxes2 = 1.0;
		
		//Test the balance calculator at december of 2013(should be -50)
		calendar= Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2014);
		calendar.set(Calendar.MONTH, 0);
		balance = balanceCalculator.getBalance(unidadFuncional, calendar.getTime());
		assertEquals("The balance is wrong",-50.0, balance, 0.005);
		
		//Test the balance calculator at january of 2014(should be -50 plus taxes)
		calendar= Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2014);
		calendar.set(Calendar.MONTH, 1);
		balance = balanceCalculator.getBalance(unidadFuncional, calendar.getTime());
		assertEquals("The balance is wrong",-50.0 + (-50.0*taxes1), balance, 0.005);
		
		// Test the balance calculator at january of 2014(should be -50 plus taxes applied 2 time positive)
		calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2014);
		calendar.set(Calendar.MONTH, 2);
		balance = balanceCalculator.getBalance(unidadFuncional,
				calendar.getTime());
		assertEquals("The balance is wrong", -50.0 + (-50.0 * taxes1) + ((-50.0 + (-50.0 * taxes1))*taxes2), balance,
				0.005);
	}
}
