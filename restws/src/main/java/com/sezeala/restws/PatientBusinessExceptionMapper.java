package com.sezeala.restws;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.sezeala.restws.exceptions.PatientBusinessException;

@Provider
public class PatientBusinessExceptionMapper implements ExceptionMapper<PatientBusinessException> {

	@Override
	public Response toResponse(PatientBusinessException exception) {
		
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"status\":\"error\",");
		sb.append("\"message\":\"Try again later\"");
		sb.append("}");
		//return Response.status(404).build();
		return Response.serverError().entity(sb.toString()).type(MediaType.APPLICATION_JSON).build();
	}

}
