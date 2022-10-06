package com.quiniela.api.repository;

import com.quiniela.api.model.Mundial;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface MundialRepository extends MongoRepository<Mundial, String> {

}
