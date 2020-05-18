package com.example.demo.repository;

import com.example.demo.entity.Group;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    Optional<List<Group>> findByIdIn(List<Long> ids);

    @Query(value = "SELECT * FROM group_meta gm INNER JOIN group_service gs ON gm.id = gs.group_id INNER JOIN service s ON s.id = gs.service_id", nativeQuery = true)
    Optional<List<Group>> findAllByServiceRequest();
}