package com.example.demo.repository;

import com.example.demo.model.Role;
import com.example.demo.model.Service;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface ServiceRepository extends JpaRepository<Service, Long> {
    @EntityGraph(value = "Service.withAccessKeys", type = EntityGraph.EntityGraphType.LOAD)
    List<Service> findAll();
}
