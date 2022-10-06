package com.quiniela.api.controller;

import java.util.List;

import com.quiniela.api.beans.QuinielaResponse;
import com.quiniela.api.model.Ligas;
import com.quiniela.api.model.Mundial;
import com.quiniela.api.model.Partido;
import com.quiniela.api.model.Predicciones;
import com.quiniela.api.model.Usuario;
import com.quiniela.api.service.QuinielaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class QuinielaController {

	@Autowired private QuinielaService service;
	
	
	@PostMapping(path="/save/usuario", produces = MediaType.APPLICATION_JSON_VALUE)
	public QuinielaResponse save(@RequestBody Usuario usuario) {
		return service.save(usuario);
	}
	
	@GetMapping("/get/usuarios")
	public List<Usuario> findAll(){
	return service.findAll();
	}
	
	@GetMapping("/get/{id}")
	public Usuario findById(@PathVariable String id) {	
		return service.findById(id);
	}
	
	@DeleteMapping(path="/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public QuinielaResponse delete(@PathVariable String id) {
		return service.deleteById(id);
	}
	
	@PutMapping("/update/usuario")
	public void update(@RequestBody Usuario usuario) {
		service.save(usuario);
	}
	
	@PostMapping(path="/send/email/", produces = MediaType.APPLICATION_JSON_VALUE)
	public QuinielaResponse sendEmail(@RequestBody Usuario usuario) {
		return service.invitationByMail(usuario);
	}
	
	/////--------------------------------ligas------------------------------------------------------
	@PostMapping(path="/save/liga", produces = MediaType.APPLICATION_JSON_VALUE)
	public QuinielaResponse save(@RequestBody Ligas liga) {
		return service.saveLiga(liga);
	}
	
	@GetMapping("/get/liga")
	public List<Ligas> findAllLigas(){
	return service.findAllLigas();
	}
	
	@GetMapping("/get/liga/{id}")
	public Ligas findByIdLigas(@PathVariable String id) {	
		return service.findByIdLiga(id);
	}
	
	@DeleteMapping("/delete/liga/{id}")
	public QuinielaResponse deleteLiga(@PathVariable String id) {
		return service.deleteLigas(id);
	}
	
///----------------------------------mundial----------------------------------------------------
	@PostMapping(path="/save/mundial", produces = MediaType.APPLICATION_JSON_VALUE)
	public QuinielaResponse saveMundial(@RequestBody Mundial mundial) {
		return service.saveMundial(mundial);
	}
	
	@GetMapping("/get/mundial")
	public List<Mundial> findMundial(){
		return service.findMundial();
	}
	
	@PostMapping(path="/save/marcador/mundia/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public QuinielaResponse saveMarcador(@RequestBody Partido partido, @PathVariable String id) {
		return service.registrarMarcador(partido, id);
	}
	
	//-------------------------------predcciones------------------------------------------
	@PostMapping(path="/save/prediccion/mundial/{idMundial}", produces = MediaType.APPLICATION_JSON_VALUE)
	public QuinielaResponse savePrediccion(@RequestBody Predicciones prediccion, @PathVariable String idMundial) {
		return service.savePrediccion(prediccion, idMundial);
	}
	
	@GetMapping("/get/prediccion")
	public List<Predicciones> findAllPredicciones(){
		return service.findAllPredicciones();
	}
	
	@PostMapping(path = "/verification/prediccion/{idPrediccion}/mundial/{idMundial}/liga/{idLiga}", produces = MediaType.APPLICATION_JSON_VALUE)
	public QuinielaResponse verification(@PathVariable String idPrediccion, @PathVariable String idMundial,@PathVariable String idLiga) {
		return service.verificarPrediccion(idPrediccion, idMundial, idLiga);
	}
}

