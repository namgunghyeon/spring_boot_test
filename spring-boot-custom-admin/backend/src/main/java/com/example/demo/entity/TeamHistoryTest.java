package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "team_history_test")
public class TeamHistoryTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private TeamTest teamTest;

    private String name;

    public TeamHistoryTest() {
    }

    public TeamHistoryTest(TeamTest teamTest, String name) {
        this.teamTest = teamTest;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public TeamTest getTeamTest() {
        return teamTest;
    }

    public String getName() {
        return name;
    }
}
