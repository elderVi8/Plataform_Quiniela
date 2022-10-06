package com.quiniela.api.mapping.impl;

import java.util.Comparator;
import java.util.List;

import com.quiniela.api.mapping.IRankingResponseMapping;
import com.quiniela.api.model.Ligas;
import com.quiniela.api.model.Ranking;
import com.quiniela.api.model.Usuario;
import com.quiniela.api.model.Winners;

import org.springframework.stereotype.Component;

@Component
public class RankingResponseMappingImpl implements IRankingResponseMapping {

	@Override
	public Ranking toResponse( Ligas liga) {
		Ranking rankingResponse = new  Ranking();
		
		rankingResponse.setRanking(toSetRanking(liga.getMiembros()));
		rankingResponse.setWinners(toSetWin(toSetRanking(liga.getMiembros()),liga.getPremioTotal()));
		return rankingResponse;
	}
	
	private List<Usuario> toSetRanking(List<Usuario> listaUsuarios){
		 listaUsuarios.sort(Comparator.comparing(Usuario::getPuntos).reversed());
		 return listaUsuarios;
	}
	
	private Winners toSetWin(List<Usuario> listaUsuarios, Double totalPremio) {
		Winners winners = new  Winners();

		if(listaUsuarios.get(0).getPuntos()==listaUsuarios.get(1).getPuntos()) {
			winners.setPrimerLugar("Usuario: "+listaUsuarios.get(0).getUsername()+" 42.5% [Q"+ totalPremio*0.425 +" ]");
			winners.setSegundoLugar("Usuario: "+listaUsuarios.get(1).getUsername()+" 42.5% [Q"+ totalPremio*0.425 +" ]");
			winners.setTercerLugar("Usuario: "+listaUsuarios.get(2).getUsername()+" 10% [Q"+ totalPremio*0.1 +" ]");
			winners.setCuartoLugar("Usuario: "+listaUsuarios.get(3).getUsername()+" 10% [Q"+ totalPremio*0.1 +" ]");
			
		}else if(listaUsuarios.get(1).getPuntos()==listaUsuarios.get(2).getPuntos()) {
			winners.setPrimerLugar("Usuario: "+listaUsuarios.get(0).getUsername()+" 50% [Q"+ totalPremio*0.5 +" ]");
			winners.setSegundoLugar("Usuario: "+listaUsuarios.get(1).getUsername()+" 17.5% [Q"+ totalPremio*0.175 +" ]");
			winners.setTercerLugar("Usuario: "+listaUsuarios.get(2).getUsername()+" 17.5% [Q"+ totalPremio*0.175 +" ]");
			winners.setCuartoLugar("Usuario: "+listaUsuarios.get(3).getUsername()+" 10% [Q"+ totalPremio*0.1 +" ]");
		
		}else if(listaUsuarios.get(2).getPuntos()==listaUsuarios.get(3).getPuntos()) {
			winners.setPrimerLugar("Usuario: "+listaUsuarios.get(0).getUsername()+" 50% [Q"+ totalPremio*0.5 +" ]");
			winners.setSegundoLugar("Usuario: "+listaUsuarios.get(1).getUsername()+" 25% [Q"+ totalPremio*0.25 +" ]");
			winners.setTercerLugar("Usuario: "+listaUsuarios.get(2).getUsername()+" 5% [Q"+ totalPremio*0.05 +" ]");
			winners.setCuartoLugar("Usuario: "+listaUsuarios.get(3).getUsername()+" 5% [Q"+ totalPremio*0.05 +" ]");
		}else {
		winners.setPrimerLugar("Usuario: "+listaUsuarios.get(0).getUsername()+" 50% [Q"+ totalPremio*0.5 +" ]");
		winners.setSegundoLugar("Usuario: "+listaUsuarios.get(1).getUsername()+" 25% [Q"+ totalPremio*0.25 +" ]");
		winners.setTercerLugar("Usuario: "+listaUsuarios.get(2).getUsername()+" 10% [Q"+ totalPremio*0.1 +" ]");
		winners.setCuartoLugar("Usuario: "+listaUsuarios.get(3).getUsername()+" 10% [Q"+ totalPremio*0.1 +" ]");
		}
		
		return winners;
	}
	
	
}
