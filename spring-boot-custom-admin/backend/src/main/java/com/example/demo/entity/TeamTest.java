package com.example.demo.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "team_test")
public class TeamTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "teamTest")
    private TeamHistoryTest teamHistoryTest;

    @Column(name = "team_history_test")
    private Long teamHistoryTestId;

    private String name;

    public TeamTest() {
    }

    public TeamTest(String name) {
        this.name = name;
    }

    public TeamTest(TeamHistoryTest teamHistoryTest, String name) {
        this.teamHistoryTest = teamHistoryTest;
        this.name = name;
    }

    public void setTeamHistory(TeamHistoryTest teamHistoryTest) {
        this.teamHistoryTest = teamHistoryTest;
    }

    public void setTeamHistoryTestId(Long teamHistoryTestId) {
        this.teamHistoryTestId = teamHistoryTestId;
    }

    public TeamHistoryTest getTeamHistoryTest() {
        return teamHistoryTest;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
