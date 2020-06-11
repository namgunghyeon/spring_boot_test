package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "team_history")
public class TeamHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable=false)
    private Team team;

    private String name;

    public TeamHistory() {
    }

    public TeamHistory(Team team, String name) {
        this.team = team;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public Team getTeam() {
        return team;
    }

    public String getName() {
        return name;
    }
}
