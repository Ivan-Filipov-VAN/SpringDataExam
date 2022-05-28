package com.example.football.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "towns")
public class Town extends BaseEntity {

    private String name;
    private Integer population;
    private String travelGuide;

    public Town() {
    }

    @Column(nullable = false, unique = true)
    public String getName() {
        return name;
    }

    @Column(nullable = false)
    public Integer getPopulation() {
        return population;
    }

    @Column(columnDefinition = "TEXT", nullable = false)
    public String getTravelGuide() {
        return travelGuide;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public void setTravelGuide(String travelGuide) {
        this.travelGuide = travelGuide;
    }
}
