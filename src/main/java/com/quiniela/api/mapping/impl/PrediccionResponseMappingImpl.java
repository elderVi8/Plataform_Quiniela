package com.quiniela.api.mapping.impl;

import java.util.ArrayList;
import java.util.List;

import com.quiniela.api.mapping.IPrediccionResponseMapping;
import com.quiniela.api.model.Grupo;
import com.quiniela.api.model.Mundial;
import com.quiniela.api.model.Partido;
import com.quiniela.api.model.Predicciones;
import com.quiniela.api.model.Usuario;

import org.springframework.stereotype.Component;

@Component
public class PrediccionResponseMappingImpl implements IPrediccionResponseMapping {

	@Override
	public Predicciones toResponsePrediccion(Predicciones prediccion, Usuario usuario, Mundial mundial) {
	Partido partidoResponse = new Partido();
	partidoResponse = toSetEquipos(toGetListaPartidos(mundial.getGrupos(),prediccion.getIdPartido()),prediccion.getIdPartido());
		
		prediccion.setId("U-"+usuario.getId()+"-"+randomNumber());
		prediccion.setUsernameUsuario(usuario.getUsername());
		prediccion.setEquipo_Puno(partidoResponse.getEquipo_uno());
		prediccion.setEquipo_Pdos(partidoResponse.getEquipo_dos());
		return prediccion;
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
	
	private Partido toSetEquipos(List<Partido> listaPartidos, String idPartido) {
		Partido partidoResponse = new Partido();
		for(Partido partido:listaPartidos) {
			if(partido.getId().equals(idPartido)) {
				partidoResponse = partido;
			}
		}
		return partidoResponse;
	}
	
	
	
	private int randomNumber() {
		int number = (int) (1000 + Math.random() * 9000);
		return number;
	}
}
