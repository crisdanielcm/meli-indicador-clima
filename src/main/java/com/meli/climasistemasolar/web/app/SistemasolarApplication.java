package com.meli.climasistemasolar.web.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.meli.climasistemasolar.web.app.models.dao.ICondicionClimaticaDao;
import com.meli.climasistemasolar.web.app.models.service.CondicionClimaticaImpl;

@SpringBootApplication
public class SistemasolarApplication implements CommandLineRunner{
	
	@Autowired
	private ICondicionClimaticaDao condicionClimaticaDao;
	
	public static void main(String[] args) {
		SpringApplication.run(SistemasolarApplication.class, args);
	}
	
	/**
	 * Se ejecutan los pronosticos al cargar la aplicacion para los proximos 10 anios.
	 */
	@Override
	public void run(String... args) throws Exception {
		CondicionClimaticaImpl condicionClimaticaImpl = new CondicionClimaticaImpl(condicionClimaticaDao);
		condicionClimaticaImpl.generarPronosticos();
	}

}
