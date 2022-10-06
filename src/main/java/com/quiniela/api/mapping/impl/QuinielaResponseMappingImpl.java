package com.quiniela.api.mapping.impl;

import com.quiniela.api.beans.QuinielaResponse;
import com.quiniela.api.mapping.IQuinielaResponseFaultMapping;

import org.springframework.stereotype.Component;

@Component
public class QuinielaResponseMappingImpl implements IQuinielaResponseFaultMapping {

	@Override
	public QuinielaResponse toResponse(String msgType, String message) {
		QuinielaResponse quinielaResponse = new  QuinielaResponse();
		quinielaResponse.setMsgType(msgType);
		quinielaResponse.setMessage(message);
		return quinielaResponse;
	}

}
