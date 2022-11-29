package com.quiniela.api.service;

import java.util.ArrayList;
import java.util.List;

import com.quiniela.api.beans.QuinielaResponse;
import com.quiniela.api.mapping.IGruposResponseMapping;
import com.quiniela.api.mapping.IPrediccionResponseMapping;
import com.quiniela.api.mapping.IQuinielaResponseFaultMapping;
import com.quiniela.api.mapping.IRankingResponseMapping;
import com.quiniela.api.mapping.IVerificacionMapping;
import com.quiniela.api.model.Ligas;
import com.quiniela.api.model.Mundial;
import com.quiniela.api.model.Partido;
import com.quiniela.api.model.Predicciones;
import com.quiniela.api.model.Usuario;
import com.quiniela.api.repository.LigasRespository;
import com.quiniela.api.repository.MundialRepository;
import com.quiniela.api.repository.PrediccionesRepository;
import com.quiniela.api.repository.UsuarioRepository;
import com.quiniela.api.utilities.ISendEmail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuinielaService {
	
	@Autowired
	private UsuarioRepository repository;
	@Autowired
	private LigasRespository ligasRepository;
	@Autowired
	private MundialRepository mundialRepository;
	@Autowired 
	private PrediccionesRepository prediccionRepository;
	@Autowired
	private IRankingResponseMapping rankingMapping;
	@Autowired
	private IGruposResponseMapping gruposMapping; 
	@Autowired 
	private IPrediccionResponseMapping prediccionMapping;
	@Autowired
	private IVerificacionMapping verificacionMapping;
	@Autowired
	private IQuinielaResponseFaultMapping fault;
	@Autowired
	private ISendEmail sendEmail;

	
	public QuinielaResponse save(Usuario usuario) {
		try {
			repository.save(usuario);
			return fault.toResponse("RESPONSE", "El usuario se registro correctamente");
		}catch(Exception e) {
			e.getStackTrace();
			return fault.toResponse("ERROR", "Fallo al registrar el usuario");
		}	
	}
	
	
	public List<Usuario> findAll(){
		return repository.findAll();
	}
	
	public Usuario findById(String id) {
		if(repository.findById(id).isEmpty()) {
			return toSetUsuario();
		}
		return repository.findById(id).get();
	}
	
	
	public QuinielaResponse deleteById(String id) {
	
		if(repository.findById(id).isEmpty()) {
			return fault.toResponse("ERROR", "Usuario no encontrado");
			
		}else {
			repository.deleteById(id);
			return fault.toResponse("RESPONSE", "El usuario se elimino correctamente");
		}	
	}
	
	
	public QuinielaResponse invitationByMail(Usuario usuario) { ////queda pendiente por el puerto 25 parece que rechaza la conexion
		
		
		if(sendEmail.sendEmailTo(usuario.getEmail(),"Unete a mi liga usuario admin: ["+usuario.getUsername()+"]")==true) {
			return fault.toResponse("RESPONSE", "Invitacion enviada correctamente");
		}else {
			return fault.toResponse("ERROR",  "Error al enviar Invitacion");
		}
		
	}
//-------------------------------------------------------ligas-------------------------------------------------------------------
	
	public QuinielaResponse saveLiga(Ligas liga) {
		List<Usuario> lista = liga.getMiembros();
		try {
			if(verifyUsers(lista)!=false) {
				if(liga.getTipoLiga().equals("apuesta")&&liga.getMiembros().size()>4) {
					liga.setPremioTotal(liga.getPrecio()*lista.size());
					liga.setRanking(rankingMapping.toResponse(liga));
					ligasRepository.save(liga);
					return fault.toResponse("RESPONSE", "La liga se registro correctamente");
				}	else if(liga.getTipoLiga().equals("diversion")) {
					liga.setPremioTotal(liga.getPrecio()*lista.size());
					liga.setRanking(rankingMapping.toResponse(liga));
					ligasRepository.save(liga);
					return fault.toResponse("RESPONSE", "La  liga se registro correctamente");
				}else {
					return fault.toResponse("ERROR", "Fallo al registrar la liga");
				}
			}else {
				return fault.toResponse("ERROR", "Fallo al registrar la liga");
			}
		}catch(Exception e) {
			e.getStackTrace();
			return fault.toResponse("ERROR", "Fallo al registrar la liga");
		}
	}
	
	public List<Ligas> findAllLigas(){
		return ligasRepository.findAll();
	}
	
	public Ligas findByIdLiga(String id) {
		if(ligasRepository.findById(id).isEmpty()) {
			return new Ligas();
		}
		return ligasRepository.findById(id).get();
	}
	
	
	public QuinielaResponse deleteLigas(String id) {
		if(ligasRepository.findById(id).isEmpty()) {
			return fault.toResponse("ERROR", "Liga no encontrada");
			
		}else {
			ligasRepository.deleteById(id);
			return fault.toResponse("RESPONSE", "La liga se elimino correctamente");
		}
	}
	
	private Usuario toSetUsuario() {
		Usuario usuario = new Usuario();
		usuario.setEmail(" ");
		usuario.setId(" ");
		usuario.setPassword(" ");
		usuario.setUsername(" ");
		return usuario;
	}
	
	private boolean verifyUsers(List<Usuario> listaUsuarios) {
		boolean flag = false;
		List<Usuario> listaVerificada = new ArrayList<>();
		
		if (listaUsuarios.isEmpty()) {
			return flag;
		} else {
			for (Usuario user : listaUsuarios) {
				if(repository.findById(user.getId()).isEmpty()) {
					flag=false;
					return flag;
				}else {
					listaVerificada.add(user);
				}
			}
		}
		if(listaVerificada.size()==listaUsuarios.size()) {
			return flag=true;
		}else{
		return flag;
		}
		
	}
	
	//---------------------------------------------------------mundial---------------------------------------------------------
	
	public QuinielaResponse saveMundial(Mundial mundial) {
		try {
			mundial.setGrupos(gruposMapping.toResponseGrupos(mundial));
			mundialRepository.save(mundial);
			return fault.toResponse("RESPONSE", "El Mundial se registro correctamente");
		}catch(Exception e) {
			e.getStackTrace();
			return fault.toResponse("ERROR", "Fallo al registrar el Mundial");
		}
		
	}
	
	public List<Mundial> findMundial() {
		return mundialRepository.findAll();
	}
	
	
	public QuinielaResponse registrarMarcador(Partido partido, String idMundial) {
		
		Mundial mundial= mundialRepository.findById(idMundial).get();
		try {
			mundial.setGrupos(gruposMapping.toSetMarcador(mundial, partido));
			mundial.setGrupos(gruposMapping.toResponseGrupos(mundial));
			mundialRepository.save(mundial);
			
			return fault.toResponse("RESPONSE", "El marcador se registro correctamente");
		}catch(Exception e) {
			e.getStackTrace();
			return fault.toResponse("ERROR", "Fallo al registrar el marcador");
		}
	}
	
	
	//---------------------------------------------------------------Predicciones------------------------------------------------
	
	public QuinielaResponse savePrediccion(Predicciones prediccion, String idMundial) {
		try {
			Mundial mundial = mundialRepository.findById(idMundial).get();
			Usuario usuario = repository.findById(prediccion.getIdUsuario()).get();
			
			prediccionRepository.save(prediccionMapping.toResponsePrediccion(prediccion, usuario, mundial));
			return fault.toResponse("RESPONSE", "El vaticinio se registro correctamente");
		} catch (Exception e) {
			e.getStackTrace();
			return fault.toResponse("ERROR", "Fallo al registrar el vaticinio.....");
		}
	}
	
	public List<Predicciones> findAllPredicciones(){
		return prediccionRepository.findAll();
	}
	
	
	public QuinielaResponse verificarPrediccion(String idPrediccion, String idMundial,String idLiga) {
		int puntosObtenidos=0;
		try {
			Predicciones prediccion= prediccionRepository.findById(idPrediccion).get();
			Mundial mundial = mundialRepository.findById(idMundial).get();
			 
			puntosObtenidos = verificacionMapping.puntosObtenidos(prediccion, mundial);
			Usuario usuario = verificacionMapping.updateUser(repository.findById(prediccion.getIdUsuario()).get(), puntosObtenidos);
			Ligas liga = ligasRepository.findById(idLiga).get();
			liga.setMiembros(verificacionMapping.updateLiga(liga,usuario));
			
			repository.save(usuario);
			liga.setRanking(rankingMapping.toResponse(liga));
			ligasRepository.save(liga);
			
			return fault.toResponse("RESPONSE", "El usuario : "+usuario.getUsername()+" obtuvo  "+puntosObtenidos+" puntos ");

		} catch (Exception e) {
			return fault.toResponse("ERROR", "Fallo al verificar el vaticinio ");
		}
	}
}
