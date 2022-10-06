package com.quiniela.api.repository;

import com.quiniela.api.model.Predicciones;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrediccionesRepository  extends MongoRepository<Predicciones, String>{

}
