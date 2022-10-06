package com.quiniela.api.mapping;

import com.quiniela.api.model.Ligas;
import com.quiniela.api.model.Ranking;

public interface IRankingResponseMapping {

	public Ranking toResponse(Ligas liga);
}
