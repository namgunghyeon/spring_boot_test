package com.example.demo.repository;

import com.example.demo.model.TsetModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface TestModelRedisRepository extends CrudRepository<TsetModel, String> {

}
