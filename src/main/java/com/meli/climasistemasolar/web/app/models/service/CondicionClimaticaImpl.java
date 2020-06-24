package com.meli.climasistemasolar.web.app.models.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meli.climasistemasolar.web.app.models.dao.ICondicionClimaticaDao;
import com.meli.climasistemasolar.web.app.models.entity.CondicionClimatica;
import com.meli.climasistemasolar.web.app.models.entity.Planeta;
import com.meli.climasistemasolar.web.app.models.entity.Punto;

@Service
public class CondicionClimaticaImpl implements ICondicionClimaticaService {
	
	@Autowired
	private ICondicionClimaticaDao condicionClimaticaDao;
	
	private Planeta vulcano, ferengi, betasoide;
	
	public static final int DIAS_ANIO = 30;
	
	public static final int ANIOS = 1;

	public CondicionClimaticaImpl(ICondicionClimaticaDao condicionClimaticaDao) {
		this.condicionClimaticaDao = condicionClimaticaDao;
		this.ferengi =new Planeta("Ferengi", 500, 1, 1);
		this.vulcano = new Planeta("Vulcano", 1000, 5, -1);
		this.betasoide = new Planeta("Betasoide", 2000, 3, 1);
	}
	
	public void generarPronosticos() {
		int totalDias = DIAS_ANIO*ANIOS;
		for (int i = 0; i < totalDias; i++) {
			this.generarPeriodosSequiaYOptimo(this.ferengi.calcularPuntoCartesiano(i), 
					this.vulcano.calcularPuntoCartesiano(i), this.betasoide.calcularPuntoCartesiano(i), i);
			System.out.println("Dia "+i+" Ferengi "+ this.ferengi.calcularPuntoCartesiano(i));
//			System.out.println("Dia "+i+" Vulcano "+ this.vulcano.calcularPuntoCartesiano(i));
//			System.out.println("Dia "+i+" Betasoide "+ this.betasoide.calcularPuntoCartesiano(i));
		}
	}
	
	/**
	 * 
	 * @param puntoFerengi
	 * @param puntoVulcano
	 * @param puntoBetasoide
	 */
	public void generarPeriodosSequiaYOptimo(Punto puntoFerengi, Punto puntoVulcano, Punto puntoBetasoide, int dia) {
		Punto puntoSol = new Punto(0, 0);
		//Se calcula la pendiente de la recta con el punto 1 y 2.
		double pendiente = (puntoVulcano.getX() - puntoFerengi.getX())/(puntoVulcano.getY() - puntoFerengi.getY());
		
		//Remplazamos el punto 1 para el primer caso en la ecuacion de la recta despejada n = y - mx
		//para calcular la ordenada
		double ordenada = puntoFerengi.getY() - pendiente*(puntoFerengi.getX());
		
		//Teniendo lo valores, evaluamos los puntos en la ecuacion de recta que pasa por dos puntos. y =mx + n
		if(puntoSol.getY() == (pendiente*puntoSol.getX() + ordenada) 
				&& puntoBetasoide.getY() == (pendiente*puntoBetasoide.getX() + ordenada)) {
			condicionClimaticaDao.save(new CondicionClimatica(dia, "Sequia"));
		} else if(puntoSol.getY() != (pendiente*puntoSol.getX() + ordenada) 
				&& puntoBetasoide.getY() == (pendiente*puntoBetasoide.getX() + ordenada)) {
			condicionClimaticaDao.save(new CondicionClimatica(dia, "Optimo"));
		}
		
		
		
	}

}
