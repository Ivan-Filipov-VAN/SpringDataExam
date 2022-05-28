package softuni.exam.models.entity;

import softuni.exam.models.entity.enums.ApartmentType;

import javax.persistence.*;

@Entity
@Table(name = "apartments")
public class Apartment extends BaseEntity {

    private ApartmentType apartmentType;
    private double area;
    private Town town;

    public Apartment() {
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "apartment_type", nullable = false)
    public ApartmentType getApartmentType() {
        return apartmentType;
    }

    @Column(name = "area", nullable = false)
    public double getArea() {
        return area;
    }

    @ManyToOne(optional = false)
    public Town getTown() {
        return town;
    }

    public void setApartmentType(ApartmentType apartmentType) {
        this.apartmentType = apartmentType;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public void setTown(Town town) {
        this.town = town;
    }
}
