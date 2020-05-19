package com.example.demo.repository;

import com.example.demo.entity.Group;
import com.example.demo.entity.Service;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
    //@EntityGraph(value = "Service.withGroups", type = EntityGraph.EntityGraphType.LOAD)
    List<Service> findByGroupsIn(List<Group> groups);
}