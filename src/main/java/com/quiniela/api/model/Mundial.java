package com.quiniela.api.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(value="mundial")
public class Mundial implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private String sede;
	private String año;
	private Date fechaInicio;
	private Date fechaFin;
	private List<Grupo> grupos;

}
