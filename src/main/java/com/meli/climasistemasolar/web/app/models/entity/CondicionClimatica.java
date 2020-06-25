/**
 * 
 */
package com.meli.climasistemasolar.web.app.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="condiciones_climaticas")
public class CondicionClimatica implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;

	private int dia;
	
	private String clima;
	
	private double perimetro;

	private static final long serialVersionUID = 1L;
	
	public CondicionClimatica() {
		super();
	}
	
	public CondicionClimatica(int dia, String clima) {
		super();
		this.dia = dia;
		this.clima = clima;
	}
	
	public CondicionClimatica(int dia, String clima, double perimetro) {
		super();
		this.dia = dia;
		this.clima = clima;
		this.perimetro = perimetro;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public String getClima() {
		return clima;
	}

	public void setClima(String clima) {
		this.clima = clima;
	}
	
	public double getPerimetro() {
		return perimetro;
	}

	public void setPerimetro(double perimetro) {
		this.perimetro = perimetro;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
