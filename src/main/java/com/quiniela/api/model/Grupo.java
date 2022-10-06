package com.quiniela.api.model;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Grupo implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private List<Equipo> equipos;
	private List<Partido> partidos;

}
