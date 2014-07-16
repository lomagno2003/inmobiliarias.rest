package com.clomagno.inmobiliarias.rest.resources.balance;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clomagno.inmobiliarias.rest.model.UnidadFuncional;
import com.clomagno.inmobiliarias.rest.repositories.UnidadFuncionalRepository;

@Controller
@RequestMapping("/balance/**")
public class BalanceRepository {
	@Autowired
	private UnidadFuncionalRepository unidadFuncionalRepository;
	
	@Autowired
	private BalanceCalculatorByMonth balanceCalculator;
	
	public void setUnidadFuncionalRepository(
			UnidadFuncionalRepository unidadFuncionalRepository) {
		this.unidadFuncionalRepository = unidadFuncionalRepository;
	}

	@RequestMapping("/{idUnidadFuncional}/{año}/{mes}")
	public @ResponseBody Balance getBalance(@PathVariable String idUnidadFuncional, @PathVariable String año, @PathVariable String mes, Model model) {
		Balance result = null;
		UnidadFuncional unidadFuncional = unidadFuncionalRepository.findOne(new Long(idUnidadFuncional));

		if(unidadFuncional!=null){
			Integer Imes = new Integer(mes);
			Integer Iaño = new Integer(año);

			Double balance = balanceCalculator.getBalance(unidadFuncional, getFirstDateOfMonth(Imes, Iaño));
			result = new Balance(balance);
		}
		return result;
	}
	
	private Date getFirstDateOfMonth(Integer mes, Integer año) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, mes);
		cal.set(Calendar.YEAR, año);
		cal.set(Calendar.DAY_OF_MONTH,
				Calendar.getInstance().getActualMinimum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}
}
