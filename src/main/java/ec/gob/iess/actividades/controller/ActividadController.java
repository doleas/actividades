package ec.gob.iess.actividades.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ec.gob.iess.actividades.model.Actividad;
import ec.gob.iess.actividades.repo.ActividadRepository;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/actividad/1.0.0")
public class ActividadController {

	@Autowired
	private ActividadRepository actividadRepository;

	DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

	@GetMapping(value = "/test")
	public ResponseEntity<String> testGet() {
		return ResponseEntity.status(HttpStatus.OK).body("Ejecutado GET");
	}

	@PostMapping(value = "/test")
	public ResponseEntity<String> testPost() {
		return ResponseEntity.status(HttpStatus.OK).body("Ejecutado POST");
	}

	// Metodo para crear actividad
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("")
	public Actividad create(@RequestBody Actividad actividad) throws ParseException {
		Actividad actividadFromDb = new Actividad();
		actividadFromDb.setNombre(actividad.getNombre());
		actividadFromDb.setFecha(formatter.parse(formatter.format(actividad.getFecha())));
		actividadFromDb.setCompletado(actividad.isCompletado());
		return actividadRepository.save(actividadFromDb);
	}

	// Metodo para listar las actividades
	@GetMapping("")
	public List<Actividad> index() {
		return (List<Actividad>) actividadRepository.findAll();
	}

	// Metodo para actualizar las actividades
	@PutMapping("{id}")
	public Actividad update(@PathVariable Integer id, @RequestBody Actividad actividad) throws ParseException {
		Actividad actividadFromDb = actividadRepository.findById(id).orElseThrow(RuntimeException::new);
		actividadFromDb.setNombre(actividad.getNombre());
		actividadFromDb.setFecha(formatter.parse(formatter.format(actividad.getFecha())));
		actividadFromDb.setCompletado(actividad.isCompletado());

		return actividadRepository.save(actividadFromDb);

	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id) {
		Actividad actividad = actividadRepository.findById(id).orElseThrow(RuntimeException::new);
		actividadRepository.delete(actividad);
		return ResponseEntity.status(HttpStatus.OK).body("Actividad " + id + " eliminada");
	}
}
