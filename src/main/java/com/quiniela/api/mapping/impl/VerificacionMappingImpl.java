package com.quiniela.api.mapping.impl;

import java.util.ArrayList;
import java.util.List;

import com.quiniela.api.mapping.IVerificacionMapping;
import com.quiniela.api.model.Grupo;
import com.quiniela.api.model.Ligas;
import com.quiniela.api.model.Mundial;
import com.quiniela.api.model.Partido;
import com.quiniela.api.model.Predicciones;
import com.quiniela.api.model.Usuario;

import org.springframework.stereotype.Component;

@Component
public class VerificacionMappingImpl implements IVerificacionMapping {

	@Override
	public int puntosObtenidos(Predicciones prediccion, Mundial mundial) {
		int puntos=0;
		List<Partido> listaPartidos= toGetListaPartidos(mundial.getGrupos(),prediccion.getIdPartido());
		for(Partido partido: listaPartidos) {
			if(partido.getId().equals(prediccion.getIdPartido())) {
				puntos=verification(partido,prediccion);
				return puntos;
			}
		}
		
		return puntos;
	}
	
	private List<Partido> toGetListaPartidos(List<Grupo> listaGrupos, String idPartido) {
		List<Partido> listaPartidos = new ArrayList<Partido>();
		String name =String.valueOf(idPartido.charAt(0));
		
		for(Grupo grupo: listaGrupos) {
			if(grupo.getName().equals(name)) {
				listaPartidos=grupo.getPartidos();	
			}
		}
		return listaPartidos;
	}
	
	private int verification(Partido partido, Predicciones prediccion) {
		int puntos=0;
		if (partido.getMarcador_uno() == prediccion.getMarcador_Puno()
				&& partido.getMarcador_dos() == prediccion.getMarcador_Pdos()) {
			puntos=3;
			return puntos;
			
		}else if(partido.getResultado().equals(prediccion.getResultado())) {
			puntos=1;
			return puntos;
		}else {
			return puntos;
		}
	}
	

	@Override
	public Usuario updateUser(Usuario user, int puntosObtenidos) {
		user.setPuntos(puntosObtenidos);
		return user;
	}

	@Override
	public List<Usuario> updateLiga(Ligas liga, Usuario user) {
		List<Usuario> listaMiembros= liga.getMiembros();
		
		for(Usuario usuario: listaMiembros) {
			if(usuario.getId().equals(user.getId())) {
				usuario.setPuntos(user.getPuntos());
			}
		}
	
		return listaMiembros;
	}

}
