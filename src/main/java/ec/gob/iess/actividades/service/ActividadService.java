package ec.gob.iess.actividades.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import ec.gob.iess.actividades.model.Actividad;
import ec.gob.iess.actividades.repo.ActividadRepository;

@Service
public class ActividadService {
	
	@Autowired
	private ActividadRepository repo;
	
	public Actividad create(Actividad actividad) {
		return repo.save(actividad);
	}
	
	public List<Actividad> listActividades(){
		return (List<Actividad>) repo.findAll();
	}
}
