package com.quiniela.api.model;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ranking implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	
	private List<Usuario> ranking;
	private Winners winners;

}
