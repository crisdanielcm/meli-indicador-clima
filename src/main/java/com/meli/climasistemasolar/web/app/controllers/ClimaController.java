/**
 * 
 */
package com.meli.climasistemasolar.web.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meli.climasistemasolar.web.app.models.dao.ICondicionClimaticaDao;

/**
 * @author DanielCruz
 *
 */

@RestController
public class ClimaController {
	
	@Autowired
	private ICondicionClimaticaDao condicionClimaticaDao;
	
	@GetMapping("/periodosSequia")
	public Double cantidadPeriodosSequia() {
		return Math.sin(5*(2*Math.PI)*0)*1000;
	}

}
