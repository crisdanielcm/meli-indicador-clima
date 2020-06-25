package com.meli.climasistemasolar.web.app.models.service;

import com.meli.climasistemasolar.web.app.models.entity.Punto;

public interface ICondicionClimaticaService {
	
	public void generarPronosticos();
	
	public void generarPeriodos(Punto puntoFerengi, Punto puntoVulcano, Punto puntoBetasoide, int dia);
	
	public boolean generarPeriodoSequia(Punto puntoFerengi, Punto puntoVulcano, Punto puntoBetasoide);
	
	public boolean generarPeriodoOptimo(Punto puntoFerengi, Punto puntoVulcano, Punto puntoBetasoide);
	
	public boolean generarPeriodoLluvia(Punto puntoFerengi, Punto puntoVulcano, Punto puntoBetasoide);
	
	public boolean perteneceALaRecta(Punto punto, double pendiente, double corte);
	
	public double calcularPendiente(Punto punto1, Punto punto2);
	
	public double calcularCorte(Punto punto, double pendiente);
	
	public double redoandearADosDigitos(double valor);
	
	public double signo(Punto puntoFerengi, Punto puntoVulcano, Punto puntoBetasoide);

}
