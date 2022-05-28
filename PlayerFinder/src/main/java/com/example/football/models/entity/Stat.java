package com.example.football.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "stats")
public class Stat extends BaseEntity{

    private float shooting;
    private float passing;
    private float endurance;

    public Stat() {
    }

    @Column(nullable = false)
    public float getShooting() {
        return shooting;
    }

    @Column(nullable = false)
    public float getPassing() {
        return passing;
    }

    @Column(nullable = false)
    public float getEndurance() {
        return endurance;
    }

    public void setShooting(float shooting) {
        this.shooting = shooting;
    }

    public void setPassing(float passing) {
        this.passing = passing;
    }

    public void setEndurance(float endurance) {
        this.endurance = endurance;
    }
}
