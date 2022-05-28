package softuni.exam.models.dto;

import softuni.exam.models.entity.Town;
import softuni.exam.models.entity.enums.ApartmentType;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ApartmentSeedDto {

    @XmlElement
    private ApartmentType apartmentType;
    @XmlElement
    private double area;
    @XmlElement
    private String town;

    @NotNull
    public ApartmentType getApartmentType() {
        return apartmentType;
    }

    @Min(40)
    public double getArea() {
        return area;
    }

    @Size(min = 2)
    public String getTown() {
        return town;
    }
}
