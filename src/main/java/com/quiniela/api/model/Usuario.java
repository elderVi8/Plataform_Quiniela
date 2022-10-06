package com.quiniela.api.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(value="usuarios")
public class Usuario implements Serializable{

	
	
	public Usuario(String id, String email, String username, String password) {
		super();
		this.id = id;
		this.email = email;
		this.username = username;
		this.password = password;
		this.puntos = 0;
	}
	
	public Usuario() {
		//TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	private String email;
	private String username;
	private String password;
	private int puntos = 0;
	
	

}
