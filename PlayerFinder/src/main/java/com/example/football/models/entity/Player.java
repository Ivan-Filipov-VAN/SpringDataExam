package com.example.football.models.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "players")
public class Player extends BaseEntity{

    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthDate;
    private Position position;
    private Town town;
    private Team team;
    private Stat stat;

    public Player() {
    }

    @Column(nullable = false)
    public String getFirstName() {
        return firstName;
    }

    @Column(nullable = false)
    public String getLastName() {
        return lastName;
    }

    @Column(nullable = false, unique = true)
    public String getEmail() {
        return email;
    }

    @Column(nullable = false)
    public LocalDate getBirthDate() {
        return birthDate;
    }

    @Enumerated(EnumType.ORDINAL)
    public Position getPosition() {
        return position;
    }

    @ManyToOne(optional = false)
    public Town getTown() {
        return town;
    }

    @ManyToOne(optional = false)
    public Team getTeam() {
        return team;
    }

    @OneToOne(optional = false)
    public Stat getStat() {
        return stat;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setStat(Stat stat) {
        this.stat = stat;
    }
}
