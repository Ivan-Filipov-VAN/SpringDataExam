package softuni.exam.models.entity;

import softuni.exam.models.entity.Agent;
import softuni.exam.models.entity.Apartment;
import softuni.exam.models.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "offers")
public class Offer extends BaseEntity {

    private BigDecimal price;
    private LocalDate publishedOn;
    private Apartment apartment;
    private Agent agent;

    public Offer() {
    }

    @Column(name = "price", nullable = false)
    public BigDecimal getPrice() {
        return price;
    }

    @Column(name = "published_on", nullable = false)
    public LocalDate getPublishedOn() {
        return publishedOn;
    }

    @ManyToOne(optional = false)
    public Apartment getApartment() {
        return apartment;
    }

    @ManyToOne(optional = false)
    public Agent getAgent() {
        return agent;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setPublishedOn(LocalDate publishedOn) {
        this.publishedOn = publishedOn;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }
}
