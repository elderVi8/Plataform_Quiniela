package com.quiniela.api.mapping;

import com.quiniela.api.beans.QuinielaResponse;

public interface IQuinielaResponseFaultMapping {

	public QuinielaResponse toResponse(String msgType, String message);
	
}
