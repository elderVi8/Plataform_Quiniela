package com.quiniela.api.mapping;

import java.util.List;

import com.quiniela.api.model.Ligas;
import com.quiniela.api.model.Mundial;
import com.quiniela.api.model.Predicciones;
import com.quiniela.api.model.Usuario;

public interface IVerificacionMapping {
	
	public int puntosObtenidos(Predicciones prediccion, Mundial mundial);
	public Usuario updateUser(Usuario user, int puntosObtenidos);
	public List<Usuario> updateLiga(Ligas liga, Usuario user);
	

}
