package softuni.exam.models.dto;

import softuni.exam.models.entity.Agent;
import softuni.exam.models.entity.Apartment;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;
import java.time.LocalDate;

@XmlAccessorType(XmlAccessType.FIELD)
public class OfferSeedDto {

    @XmlElement
    private BigDecimal price;
    @XmlElement
    private String publishedOn;
    @XmlElement(name = "agent")
    private AgentNameDto agent;
    @XmlElement(name = "apartment")
    private ApartmentIdDto apartment;

    @Positive
    public BigDecimal getPrice() {
        return price;
    }

    @Pattern(regexp = "[\\d]{2}\\/[\\d]{2}\\/[\\d]{4}")
    public String getPublishedOn() {
        return publishedOn;
    }

    @NotNull
    public AgentNameDto getAgent() {
        return agent;
    }

    @NotNull
    public ApartmentIdDto getApartment() {
        return apartment;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setPublishedOn(String publishedOn) {
        this.publishedOn = publishedOn;
    }

    public void setAgent(AgentNameDto agent) {
        this.agent = agent;
    }

    public void setApartment(ApartmentIdDto apartment) {
        this.apartment = apartment;
    }
}
