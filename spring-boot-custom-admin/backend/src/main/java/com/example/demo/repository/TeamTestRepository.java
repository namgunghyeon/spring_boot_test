package com.example.demo.repository;

import com.example.demo.entity.TeamHistoryTest;
import com.example.demo.entity.TeamTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamTestRepository  extends JpaRepository<TeamTest, Long> {
}
