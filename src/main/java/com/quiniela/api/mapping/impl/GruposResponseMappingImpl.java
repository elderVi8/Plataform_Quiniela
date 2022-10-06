package com.quiniela.api.mapping.impl;

import java.util.Comparator;
import java.util.List;

import com.quiniela.api.mapping.IGruposResponseMapping;
import com.quiniela.api.model.Equipo;
import com.quiniela.api.model.Grupo;
import com.quiniela.api.model.Mundial;
import com.quiniela.api.model.Partido;

import org.springframework.stereotype.Component;

@Component
public class GruposResponseMappingImpl implements IGruposResponseMapping {

	@Override
	public List<Grupo> toResponseGrupos(Mundial mundial) {
		int contG=0;
		List<Grupo> listaGrupoResponse = mundial.getGrupos();
		for(Grupo grupo: listaGrupoResponse) {
			grupo.setEquipos(toOrderEquipos(grupo.getEquipos()));
			grupo.setPartidos(toSetPartido(grupo.getPartidos(),listaGrupoResponse,contG));
			contG++;
		}
		
		
		return listaGrupoResponse;
	}
	
	
	private List<Equipo> toOrderEquipos(List<Equipo> listaEquipos){
		listaEquipos.sort(Comparator.comparing(Equipo::getPuntos).reversed());
		 return listaEquipos;
		
	}
	
	private List<Partido> toSetPartido(List<Partido> listaPartidos, List<Grupo> listaGrupos, int contG){
		int cont=1;
		for(Partido partido: listaPartidos) {
			partido.setId(listaGrupos.get(contG).getName()+cont);
			cont++;
			
		}
		return listaPartidos;
	}

//--------------------------------------------------------------------------------------------------------------------------------
	@Override
	public List<Grupo> toSetMarcador(Mundial mundial, Partido partido) {
		List<Grupo> listaGrupoResponse = mundial.getGrupos();
		
		for(Grupo grupo: listaGrupoResponse) {
		grupo.setPartidos(toSetMarcador(grupo.getPartidos(),partido,grupo.getEquipos()));	
			
			
		}
		return listaGrupoResponse;
	}
	
	private List<Partido> toSetMarcador(List<Partido> listaPartidos, Partido partido, List<Equipo> listaEquipos){
		for(Partido partidoUpdate: listaPartidos) {
			if(partidoUpdate.getId().equals(partido.getId())) {
				partidoUpdate.setMarcador_uno(partido.getMarcador_uno());
				partidoUpdate.setMarcador_dos(partido.getMarcador_dos()); 
				partidoUpdate.setResultado(toSetResultado(partidoUpdate));
				toSetEquipoWin(listaEquipos, partidoUpdate);
				
			}
		}
		return listaPartidos;
	}
	
	private void toSetEquipoWin(List<Equipo> listaEquipos, Partido partido){
	
		for (Equipo equipo: listaEquipos) {
			if (partido.getMarcador_uno()>partido.getMarcador_dos()) {
				if(partido.getEquipo_uno().equals(equipo.getName())) {
					equipo.setPuntos(equipo.getPuntos()+3);
				}
			}else if(partido.getMarcador_uno()<partido.getMarcador_dos()) {
				if(partido.getEquipo_dos().equals(equipo.getName())) {
					equipo.setPuntos(equipo.getPuntos()+3);
				}
			}else {
				if(partido.getEquipo_dos().equals(equipo.getName())||partido.getEquipo_uno().equals(equipo.getName())) {
					equipo.setPuntos(equipo.getPuntos()+1);
				}
			}
		}
	}
	
	private String toSetResultado(Partido partido) {
		String resul = "";
		if (partido.getMarcador_uno() > partido.getMarcador_dos()) {
			resul = partido.getEquipo_uno();
		} else if (partido.getMarcador_uno() < partido.getMarcador_dos()) {
			resul = partido.getEquipo_dos();
		} else {
			resul = "EMPATE";
		}
		return resul;
	}

}
