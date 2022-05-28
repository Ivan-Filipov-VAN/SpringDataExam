package com.example.football.models.dto;

import com.example.football.models.entity.Town;
import com.google.gson.annotations.Expose;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class TeamSeedDto {

    @Expose
    private String name;
    @Expose
    private String stadiumName;
    @Expose
    private Integer fanBase;
    @Expose
    private String history;
    @Expose
    private String townName;

    @Size(min = 3)
    public String getName() {
        return name;
    }

    @Size(min = 3)
    public String getStadiumName() {
        return stadiumName;
    }

    @Min(1000)
    public Integer getFanBase() {
        return fanBase;
    }

    @Size(min = 10)
    public String getHistory() {
        return history;
    }

    @Size(min = 2)
    public String getTown() {
        return townName;
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

    public void setTown(String town) {
        this.townName = town;
    }
}
