package com.meli.climasistemasolar.web.app.models.entity;

public class ResponseLluvia {

	private int cantidadPeriodo;
	
	private String clima;
	
	private int diaPicoLluvia;
	
	private double perimetro;
	
	public ResponseLluvia() {
		super();
	}
	
	public ResponseLluvia(int cantidadPeriodo, String clima, int diaPicoLluvia, double perimetro) {
		super();
		this.cantidadPeriodo = cantidadPeriodo;
		this.clima = clima;
		this.diaPicoLluvia = diaPicoLluvia;
		this.perimetro = perimetro;
	}

	public int getCantidadPeriodo() {
		return cantidadPeriodo;
	}

	public void setCantidadPeriodo(int cantidadPeriodo) {
		this.cantidadPeriodo = cantidadPeriodo;
	}

	public String getClima() {
		return clima;
	}

	public void setClima(String clima) {
		this.clima = clima;
	}

	public int getDiaPicoLluvia() {
		return diaPicoLluvia;
	}

	public void setDiaPicoLluvia(int diaPicoLluvia) {
		this.diaPicoLluvia = diaPicoLluvia;
	}

	public double getPerimetro() {
		return perimetro;
	}

	public void setPerimetro(double perimetro) {
		this.perimetro = perimetro;
	}
	
	

}
