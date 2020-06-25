/**
 * 
 */
package com.meli.climasistemasolar.web.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.meli.climasistemasolar.web.app.models.dao.ICondicionClimaticaDao;
import com.meli.climasistemasolar.web.app.models.entity.CondicionClimatica;
import com.meli.climasistemasolar.web.app.models.entity.ResponseLluvia;
import com.meli.climasistemasolar.web.app.models.entity.ResponsePeriodo;

/**
 * @author DanielCruz
 *
 */

@RestController
@RequestMapping("/api/meli")
public class ClimaController {

	@Autowired
	private ICondicionClimaticaDao condicionClimaticaDao;

	@GetMapping("/cantidad_climatica")
	public ResponseEntity<Object> contarCantidadPorClima(@RequestParam("clima") String clima) {

		try {
			return new ResponseEntity<>(new ResponsePeriodo(condicionClimaticaDao.countByClima(clima), clima),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Ocurrio un error, intente mas tarde.", HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/pronostico_lluvia")
	public ResponseEntity<Object> obtenerPronosticoLluvia() {
		try {
			List<CondicionClimatica> periodosLluvia = condicionClimaticaDao.findByClimaOrderByPerimetroDesc("Lluvia");
			if (periodosLluvia.size() > 0) {
				return new ResponseEntity<>(new ResponseLluvia(periodosLluvia.size(), "Lluvia",
						periodosLluvia.get(0).getDia(), periodosLluvia.get(0).getPerimetro()), HttpStatus.OK);
			}
			 return new ResponseEntity<>("No se encontraron resultados.", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Ocurrio un error, intente mas tarde.", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/pronostico_dia")
	public ResponseEntity<Object> obtenerPronosticoPorDia(@RequestParam("dia") int dia) {
		try {
			CondicionClimatica condicion = condicionClimaticaDao.findByDia(dia);
			if(condicion != null) {
				return new ResponseEntity<>(condicion, HttpStatus.OK);
			}
			return new ResponseEntity<>("No se encontraron resultados para el dia especificado.", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Ocurrio un error, intente mas tarde.", HttpStatus.BAD_REQUEST);
		}
	}

}
