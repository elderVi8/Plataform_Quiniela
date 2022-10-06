package com.quiniela.api.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Winners implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String primerLugar;
	private String segundoLugar;
	private String tercerLugar;
	private String cuartoLugar;

}
