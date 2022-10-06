package com.quiniela.api.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(value="ligas")
public class Ligas implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private String name;
	private Usuario usuarioAdmin;
	private List<Usuario> miembros;
	private String tipoLiga;
	private Double precio;
	
	private Double premioTotal;
	private Ranking ranking;
	
	

}
