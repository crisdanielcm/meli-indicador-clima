package com.meli.climasistemasolar.web.app.models.entity;

public class Triangulo {

	private Punto punto1, punto2, punto3;

	public Triangulo(Punto punto1, Punto punto2, Punto punto3) {
		super();
		this.punto1 = punto1;
		this.punto2 = punto2;
		this.punto3 = punto3;
	}

	public double calcularPerimetro() {
		return punto1.calcularDistanciaEntreDosPuntos(punto2) + punto1.calcularDistanciaEntreDosPuntos(punto3)
				+ punto2.calcularDistanciaEntreDosPuntos(punto3);
	}

}
