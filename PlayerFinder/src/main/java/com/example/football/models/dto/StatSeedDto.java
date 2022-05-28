package com.example.football.models.dto;

import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class StatSeedDto {

    @XmlElement
    private float shooting;
    @XmlElement
    private float passing;
    @XmlElement
    private float endurance;

    @Positive
    public float getShooting() {
        return shooting;
    }

    @Positive
    public float getPassing() {
        return passing;
    }

    @Positive
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
