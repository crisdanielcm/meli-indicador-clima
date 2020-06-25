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
 * @author Cristian Daniel Cruz M
 *
 */

@RestController
@RequestMapping("/api/meli")
public class ClimaController {

	@Autowired
	private ICondicionClimaticaDao condicionClimaticaDao;
	
	/**
	 * Servicio que retorna la cantidad de periodos de un clima en especifico para el sistema solar.
	 * @param clima nombre del clima a consultar
	 * @return JSON {"cantidadPeriodo":cantidad,"clima":"clima"}
	 */
	@GetMapping("/cantidad_climatica")
	public ResponseEntity<Object> contarCantidadPorClima(@RequestParam("clima") String clima) {

		try {
			return new ResponseEntity<>(new ResponsePeriodo(condicionClimaticaDao.countByClima(clima), clima),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Ocurrio un error, intente mas tarde.", HttpStatus.BAD_REQUEST);
		}

	}
	
	/**
	 * Servicio que retorna la cantidad de periodos de lluvia, el dia donde se alcanza el pico maximo y el 
	 * perimetro maximo
	 * @return JSON {"cantidadPeriodo":cantidad,"clima":clima,"diaPicoLluvia":dia,"perimetro":perimetro}
	 */
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
	
	/**
	 * Servicio que retorna el pronostico dado un dia
	 * @param dia dia del pronostico a consultar
	 * @return JSON {"dia":590,"clima":"Lluvia","perimetro":4401.486757023873}
	 */
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
