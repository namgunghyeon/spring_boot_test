package com.example.demo.repository;

import com.example.demo.model.TsetModel;
import org.springframework.data.repository.CrudRepository;

public interface TestModelRedisRepository extends CrudRepository<TsetModel, String> {

}
