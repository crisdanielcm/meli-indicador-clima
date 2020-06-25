package com.meli.climasistemasolar.web.app.models.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meli.climasistemasolar.web.app.models.dao.ICondicionClimaticaDao;
import com.meli.climasistemasolar.web.app.models.entity.CondicionClimatica;
import com.meli.climasistemasolar.web.app.models.entity.Planeta;
import com.meli.climasistemasolar.web.app.models.entity.Punto;
import com.meli.climasistemasolar.web.app.models.entity.Triangulo;

@Service
public class CondicionClimaticaImpl implements ICondicionClimaticaService {

	@Autowired
	private ICondicionClimaticaDao condicionClimaticaDao;

	private Planeta vulcano, ferengi, betasoide;

	private Punto puntoSol;

	public static final int DIAS_ANIO = 360;

	public static final int ANIOS = 10;

	public CondicionClimaticaImpl(ICondicionClimaticaDao condicionClimaticaDao) {
		this.condicionClimaticaDao = condicionClimaticaDao;
		this.ferengi = new Planeta("Ferengi", 500, 1, 1);
		this.vulcano = new Planeta("Vulcano", 1000, 5, -1);
		this.betasoide = new Planeta("Betasoide", 2000, 3, 1);
		this.puntoSol = new Punto(0, 0);
	}

	/**
	 * Metodo que genera los pronosticos para 10 anios en adelante para los planetas instanciados.
	 */
	public void generarPronosticos() {
		int totalDias = DIAS_ANIO * ANIOS;
		for (int i = 0; i < totalDias; i++) {
			this.generarPeriodos(this.ferengi.calcularPuntoCartesiano(i),
					this.vulcano.calcularPuntoCartesiano(i), this.betasoide.calcularPuntoCartesiano(i), i);
		}
	}

	/**
	 * Metodo que se encarga de almacenar en base de datos local, las condiciones climaticas de acuerdo a las condiciones dadas.
	 * @param puntoFerengi punto cartesiano que representa el planeta Ferengi
	 * @param puntoVulcano punto cartesiano que representa el planeta Vulcano
	 * @param puntoBetasoide punto cartesiano que representa el planeta Betasoide
	 * @param dia dia en que se genera el periodo
	 */
	public void generarPeriodos(Punto puntoFerengi, Punto puntoVulcano, Punto puntoBetasoide, int dia) {
		
		if (generarPeriodoSequia(puntoFerengi, puntoVulcano, puntoBetasoide)) {
			condicionClimaticaDao.save(new CondicionClimatica(dia, "Sequia"));
		} else if (generarPeriodoOptimo(puntoFerengi, puntoVulcano, puntoBetasoide)) {
			condicionClimaticaDao.save(new CondicionClimatica(dia, "Optimo"));
		} else if (generarPeriodoLluvia(puntoFerengi, puntoVulcano, puntoBetasoide)) {
			Triangulo triangulo = new Triangulo(puntoFerengi, puntoVulcano, puntoBetasoide);
			condicionClimaticaDao.save(new CondicionClimatica(dia, "Lluvia", triangulo.calcularPerimetro()));
		}else {
			condicionClimaticaDao.save(new CondicionClimatica(dia, "indefinido"));
		}
	}
	
	/**
	 * Metodo que comprueba el periodo de sequia a partir de la ecuacion de la recta. Se evaluan los puntos para calcular las diferentes
	 * variables y determinar si pertenecen a la recta que alinea a todos los planetas incluyendo el sol.
	 * @param puntoFerengi punto cartesiano que representa el planeta Ferengi
	 * @param puntoVulcano punto cartesiano que representa el planeta Vulcano
	 * @param puntoBetasoide punto cartesiano que representa el planeta Betasoide
	 * @return retorna true si se cumplen las condiciones de alineacion, de lo contrario false.
	 */
	public boolean generarPeriodoSequia(Punto puntoFerengi, Punto puntoVulcano, Punto puntoBetasoide) {
		// Se calcula la pendiente de la recta con el punto 1 y 2.
		double pendiente = this.calcularPendiente(puntoFerengi, puntoBetasoide);

		if (pendiente == Double.POSITIVE_INFINITY || pendiente == Double.POSITIVE_INFINITY) {
			pendiente = 0;
		}

		// Remplazamos el puntoFerengi para el primer caso en la ecuacion de la recta
		// despejada b = y - mx para calcular el corte.
		double corte = this.calcularCorte(puntoFerengi, pendiente);
		// Teniendo lo valores, evaluamos los puntos en la ecuacion de recta que pasa
		// por dos puntos. y =mx + b
		if (this.perteneceALaRecta(puntoSol, pendiente, corte)
				&& this.perteneceALaRecta(puntoVulcano, pendiente, corte)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Metodo que comprueba el periodo de condiciones optimas de presion a aprtir de la ecuacion de la recta, sin incluir el puntos
	 * del sol. A partir de la ecuacion se determina si los planetas estan alineados entre si.
	 * @param puntoFerengi punto cartesiano que representa el planeta Ferengi
	 * @param puntoVulcano punto cartesiano que representa el planeta Vulcano
	 * @param puntoBetasoide punto cartesiano que representa el planeta Betasoide
	 * @return retorna true si se cumplen las condiciones de alineacion, de lo contrario false.
	 */
	public boolean generarPeriodoOptimo(Punto puntoFerengi, Punto puntoVulcano, Punto puntoBetasoide) {
		// Se calcula la pendiente de la recta con el punto 1 y 2.
		double pendiente = this.calcularPendiente(puntoFerengi, puntoBetasoide);

		if (pendiente == Double.POSITIVE_INFINITY || pendiente == Double.POSITIVE_INFINITY) {
			pendiente = 0;
		}

		// Remplazamos el puntoFerengi para el primer caso en la ecuacion de la recta
		// despejada b = y - mx para calcular el corte.
		double corte = this.calcularCorte(puntoFerengi, pendiente);
		// Teniendo lo valores, evaluamos los puntos en la ecuacion de recta que pasa
		// por dos puntos. y =mx + b
		if (!this.perteneceALaRecta(puntoSol, pendiente, corte)
				&& this.perteneceALaRecta(puntoVulcano, pendiente, corte)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Metodo que determina si el punto del sol se encuentra entre los distintos triangulos que generan los planetas al pasar
	 * de los dias. Se usa formula basada en las distancias y los signos, que determinan el lado en el que se encuentra el punto.
	 * @param puntoFerengi punto cartesiano que representa el planeta Ferengi
	 * @param puntoVulcano punto cartesiano que representa el planeta Vulcano
	 * @param puntoBetasoide punto cartesiano que representa el planeta Betasoide
	 * @return retorna true si se cumplen las condiciones, de lo contrario false.
	 */
	public boolean generarPeriodoLluvia(Punto puntoFerengi, Punto puntoVulcano, Punto puntoBetasoide) {
		double distancia1 = signo(puntoSol, puntoVulcano, puntoBetasoide);
		double distancia2 = signo(puntoSol, puntoFerengi, puntoBetasoide);
		double distancia3 = signo(puntoSol, puntoVulcano, puntoFerengi);

		boolean negativo = (distancia1 < 0 || distancia2 < 0 || distancia3 < 0);
		boolean positivo = (distancia1 > 0 || distancia2 > 0 || distancia3 > 0);

		if ((negativo && positivo)) {
			return true;
		}
		return false;

	}

	/**
	 * Funcion que permite conocer si un punto pertenece a una recta. Se reemplaza
	 * un punto en la ecuacion de la recta y si se da la equivalencia retorna true,
	 * de lo contrario false.
	 * 
	 * @param punto     punto x, y que se va a reemplazar en la ecuacion.
	 * @param pendiente pendiente de la recta.
	 * @param corte     corte en el eje Y.
	 * @return Retorna true si se da la equivalencia y false en caso contrario.
	 */
	public boolean perteneceALaRecta(Punto punto, double pendiente, double corte) {
		return punto.getY() == (pendiente * punto.getX() + corte);
	}

	/**
	 * Metodo que permite calcular la pendiente de una recta dados dos puntos.
	 * @param punto1
	 * @param punto2
	 * @return retorna el valor de la pendiente.
	 */
	public double calcularPendiente(Punto punto1, Punto punto2) {
		return redoandearADosDigitos((punto2.getY() - punto1.getY()) / (punto2.getX() - punto1.getX()));
	}

	/**
	 * Metodo que permite calcular el corte en el eje Y de una recta dado un punto y
	 * la pendiente.
	 * @param punto
	 * @param pendiente
	 * @return retorna el valor del corte en el eje Y.
	 */
	public double calcularCorte(Punto punto, double pendiente) {
		return redoandearADosDigitos(punto.getY() - pendiente * (punto.getX()));
	}

	/**
	 * Metodo que redondea un numero decimal a dos digitos.
	 * @param valor
	 * @return retorna el valor redondeado a dos digitos.
	 */
	public double redoandearADosDigitos(double valor) {
		return Math.round(valor * 100d) / 100d;
	}

	/**
	 * Metodo que determina el signo de los lados a partir de la formula aplicada.
	 * @param puntoFerengi
	 * @param puntoVulcano
	 * @param puntoBetasoide
	 * @return
	 */
	public double signo(Punto puntoFerengi, Punto puntoVulcano, Punto puntoBetasoide) {
		return (puntoFerengi.getX() - puntoBetasoide.getX()) * (puntoVulcano.getY() - puntoBetasoide.getY())
				- (puntoVulcano.getX() - puntoBetasoide.getX()) * (puntoFerengi.getY() - puntoBetasoide.getY());
	}

}
