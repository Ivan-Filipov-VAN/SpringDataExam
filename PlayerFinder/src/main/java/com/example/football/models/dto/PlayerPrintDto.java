package com.example.football.models.dto;

import com.example.football.models.entity.Position;

public class PlayerPrintDto {

    private String fullName;
    private Position position;
    private String team;
    private String stadiumn;

    public PlayerPrintDto(String fullName, Position position, String team, String stadiumn) {
        this.fullName = fullName;
        this.position = position;
        this.team = team;
        this.stadiumn = stadiumn;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getStadiumn() {
        return stadiumn;
    }

    public void setStadiumn(String stadiumn) {
        this.stadiumn = stadiumn;
    }

    @Override
    public String toString() {
        return String.format("Player - %s%n" +
                "\tPosition - %s%n" +
                "\tTeam - %s%n" +
                "\tStadium - %s",
                fullName, position, team, stadiumn);
    }
}
