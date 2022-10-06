package com.quiniela.api.mapping;

import com.quiniela.api.model.Mundial;
import com.quiniela.api.model.Predicciones;
import com.quiniela.api.model.Usuario;

public interface IPrediccionResponseMapping {

	public Predicciones toResponsePrediccion(Predicciones prediccion, Usuario usuario, Mundial mundial);
		
	
}
