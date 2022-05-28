package com.example.football.models.dto;

import com.example.football.models.entity.Position;
import com.example.football.models.entity.Stat;
import com.example.football.models.entity.Team;
import com.example.football.models.entity.Town;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.time.LocalDate;

@XmlAccessorType(XmlAccessType.FIELD)
public class PlayerSeedDto {

    @XmlElement(name = "first-name")
    private String firstName;
    @XmlElement(name = "last-name")
    private String lastName;
    @XmlElement
    private String email;
    @XmlElement(name = "birth-date")
    private String birthDate;
    @XmlElement
    private Position position;
    @XmlElement
    private TownNameDto town;
    @XmlElement
    private TeamNameDto team;
    @XmlElement
    private StatIdDto stat;

    @Size(min = 2)
    public String getFirstName() {
        return firstName;
    }

    @Size(min = 2)
    public String getLastName() {
        return lastName;
    }

    @Email
    public String getEmail() {
        return email;
    }

    @Pattern(regexp = "[\\d]{2}\\/[\\d]{2}\\/[\\d]{4}")
    public String getBirthDate() {
        return birthDate;
    }

    @NotNull
    public Position getPosition() {
        return position;
    }

    @NotNull
    public TownNameDto getTown() {
        return town;
    }

    @NotNull
    public TeamNameDto getTeam() {
        return team;
    }

    @NotNull
    public StatIdDto getStat() {
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

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setTown(TownNameDto town) {
        this.town = town;
    }

    public void setTeam(TeamNameDto team) {
        this.team = team;
    }

    public void setStat(StatIdDto stat) {
        this.stat = stat;
    }
}
