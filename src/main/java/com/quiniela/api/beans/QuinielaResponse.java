package com.quiniela.api.beans;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuinielaResponse implements Serializable{

	private static final long serialVersionUID = 1L;

	private String msgType;
	private String message;
	
}
