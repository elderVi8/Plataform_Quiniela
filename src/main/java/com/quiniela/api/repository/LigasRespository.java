package com.quiniela.api.repository;

import com.quiniela.api.model.Ligas;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LigasRespository extends MongoRepository<Ligas, String> {

}
