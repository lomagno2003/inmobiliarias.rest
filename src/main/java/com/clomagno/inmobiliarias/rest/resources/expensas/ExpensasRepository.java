package com.clomagno.inmobiliarias.rest.resources.expensas;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/expensa/**")
public class ExpensasRepository {
	@RequestMapping("/{idExpensa}/")
	public @ResponseBody Expensa findPet(@PathVariable String idExpensa, Model model) {
		return new Expensa(50l);
	}
}
