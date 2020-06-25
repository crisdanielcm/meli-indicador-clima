/**
 * 
 */
package com.meli.climasistemasolar.web.app.models.entity;

/**
 * @author DanielCruz
 *
 */
public class Punto {
	
	private double x, y;

	public Punto(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Metodo que calcula la distancia entre dos puntos.
	 * @param punto punto de destino necesario para calcula la distancia.
	 * @return distancia entre dos puntos
	 */
	public double calcularDistanciaEntreDosPuntos(Punto punto) {
		return Math.sqrt(Math.pow((punto.getX() - this.x), 2) + Math.pow((punto.getY() - this.y), 2));
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	@Override
	public String toString() {
		
		return "X = "+ this.x + " Y = "+ this.y;
	}
	
	

}
