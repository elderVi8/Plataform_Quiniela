package com.quiniela.api.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Partido implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	
	public Partido(String id, Date fecha, String estadio, String equipo_uno, String equipo_dos, int marcador_uno,
			int marcador_dos, String resultado) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.estadio = estadio;
		this.equipo_uno = equipo_uno;
		this.equipo_dos = equipo_dos;
		this.marcador_uno = marcador_uno;
		this.marcador_dos = marcador_dos;
		this.resultado = resultado;
	}
	
	public Partido() {}
	
	private String id;
	private Date fecha;
	private String estadio;
	private String equipo_uno;
	private String equipo_dos;
	private int marcador_uno;
	private int marcador_dos;
	private String resultado;
	

}
