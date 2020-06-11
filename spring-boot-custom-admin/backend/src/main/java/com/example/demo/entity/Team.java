package com.example.demo.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "team")
    private Set<TeamHistory> teamHistory;

    private String name;

    public Team() {
    }

    public Team(String name) {
        this.name = name;
    }

    public Team(Set<TeamHistory> teamHistory, String name) {
        this.teamHistory = teamHistory;
        this.name = name;
    }

    public void setTeamHistory(Set<TeamHistory> teamHistory) {
        this.teamHistory = teamHistory;
    }

    public Set<TeamHistory> getTeamHistory() {
        return teamHistory;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }


}
