package com.example.demo.repository;

import com.example.demo.model.Test;
import org.springframework.data.couchbase.repository.CouchbasePagingAndSortingRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository  extends CrudRepository<Test, String> {
}
