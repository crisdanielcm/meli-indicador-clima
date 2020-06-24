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
