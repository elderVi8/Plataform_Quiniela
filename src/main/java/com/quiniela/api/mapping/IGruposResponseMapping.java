package com.quiniela.api.mapping;

import java.util.List;

import com.quiniela.api.model.Grupo;
import com.quiniela.api.model.Mundial;
import com.quiniela.api.model.Partido;

public interface IGruposResponseMapping {

	public List<Grupo> toResponseGrupos(Mundial mundial);
	public List<Grupo> toSetMarcador(Mundial mundial, Partido partido);
}
