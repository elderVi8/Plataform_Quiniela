package com.quiniela.api.repository;

import com.quiniela.api.model.Usuario;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String>{

}
