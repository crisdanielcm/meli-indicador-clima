/**
 * 
 */
package com.meli.climasistemasolar.web.app.models.entity;

/**
 * @author DanielCruz
 *
 */
public class Planeta {
	
	private String nombre;
	
	private int distancia, velocidadGrados, sentido;

	
	public Planeta(String nombre, int distancia, int velocidadGrados, int sentido) {
		super();
		this.nombre = nombre;
		this.distancia = distancia;
		this.velocidadGrados = velocidadGrados;
		this.sentido = sentido;
	}
	
	/**
	 * Método que calcula la coordenada cartesiana de un planeta en un día específico
	 * @param dia tiempo en que se calculara la coordenada
	 * @return Punto, objeto de tipo punto con la coordenada x, y.
	 */
	public Punto calcularPuntoCartesiano(int dia) {
		
		// Convierto la velocidad angular en radianes para usarla en la ecuación de 
		// conversión de coordenadas polares a cartesianas
		 
		double velocidadRadianes = ((Math.PI*this.velocidadGrados)/180)*this.sentido*dia;

		double x = Math.cos(velocidadRadianes)*this.distancia;
		double y = Math.sin(velocidadRadianes)*this.distancia;
		
		x = Math.round(x*100d)/100d;
		y = Math.round(y*100d)/100d;
		return new Punto(x, y);
	}
	

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getDistancia() {
		return distancia;
	}

	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}

	public int getVelocidadAngularDia() {
		return velocidadGrados;
	}

	public void setVelocidadAngularDia(int velocidadAngularDia) {
		this.velocidadGrados = velocidadAngularDia;
	}

	public int getSentido() {
		return sentido;
	}

	public void setSentido(int sentido) {
		this.sentido = sentido;
	}
	
	

}
