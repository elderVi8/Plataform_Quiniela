package com.quiniela.api.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(value="predicciones")
public class Predicciones implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private String idUsuario;
	private String usernameUsuario;
	private String idPartido;
	private String equipo_Puno;
	private String equipo_Pdos;
	private int marcador_Puno;
	private int marcador_Pdos;
	private String resultado;
	
	
	
}
