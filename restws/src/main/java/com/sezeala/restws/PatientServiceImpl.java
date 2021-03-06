package com.sezeala.restws;

import java.util.*;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.sezeala.restws.exceptions.PatientBusinessException;
import com.sezeala.restws.model.Patient;

import org.springframework.stereotype.Service;

@Service()
public class PatientServiceImpl implements PatientService {

	Map<Long, Patient> patients = new HashMap<>();
	long cuurentId = 123;
	public PatientServiceImpl() {
		init();
	}
	
	void init() {
		Patient patient = new Patient();
		
		patient.setId(cuurentId);
		patient.setName("John");
		patients.put(patient.getId(), patient);
	}

	@Override
	public List<Patient> getPatients() {
	    Collection<Patient>	results = patients.values();
	    List<Patient> response = new ArrayList<>(results);
	    return response;
	}

	@Override
	public Patient getPatient(Long id) {
		
		if(patients.get(id) == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return patients.get(id);
	}

	@Override
	public Response createPatient(Patient patient) {
		patient.setId(++cuurentId);
		patients.put(patient.getId(),patient);
		return Response.ok(patient).build();
	}

	@Override
	public Response updatePatient(Patient patient) {
		
		Patient currentPatient = patients.get(patient.getId());
		
		Response response;
		if(currentPatient != null) {
			patients.put(patient.getId(), patient);
			
			response = Response.ok().build();
		}else {
			throw new PatientBusinessException();
			//response = Response.notModified().build();
		}
		return response;
	}

	@Override
	public Response deletePatient(Long id) {
		Patient currentPatient = patients.get(id);
		
		Response response;
		if(currentPatient != null) {
			patients.remove(id);
			
			response = Response.ok().build();
		}else {
			response = Response.notModified().build();
		}
		return response;
	}
}
