package com.quiniela.api.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Equipo implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private int puntos;
	

}
