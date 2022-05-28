package com.example.football.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "teams")
public class Team extends BaseEntity{

    private String name;
    private String stadiumName;
    private Integer fanBase;
    private String history;
    private Town town;

    public Team() {
    }

    @Column(nullable = false, unique = true)
    public String getName() {
        return name;
    }

    @Column(nullable = false)
    public String getStadiumName() {
        return stadiumName;
    }

    @Column(nullable = false)
    public Integer getFanBase() {
        return fanBase;
    }

    @Column(columnDefinition = "TEXT", nullable = false)
    public String getHistory() {
        return history;
    }

    @ManyToOne(optional = false)
    public Town getTown() {
        return town;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
    }

    public void setFanBase(Integer fanBase) {
        this.fanBase = fanBase;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public void setTown(Town town) {
        this.town = town;
    }
}
