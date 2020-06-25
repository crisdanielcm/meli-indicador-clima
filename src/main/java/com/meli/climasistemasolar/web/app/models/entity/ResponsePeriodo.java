package com.meli.climasistemasolar.web.app.models.entity;

public class ResponsePeriodo {
	
	private int cantidadPeriodo;
	
	private String clima;
	
	public ResponsePeriodo(int cantidadPeriodo, String clima) {
		super();
		this.cantidadPeriodo = cantidadPeriodo;
		this.clima = clima;
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

}
