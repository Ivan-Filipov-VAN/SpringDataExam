package softuni.exam.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "agents")
public class Agent extends BaseEntity {

    private String firstName;
    private String lastName;
    private String email;
    private Town town;

    public Agent() {
    }

    @Column(name = "first_name", nullable = false, unique = true)
    public String getFirstName() {
        return firstName;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }

    @Column(name = "email", nullable = false, unique = true)
    public String getEmail() {
        return email;
    }

    @ManyToOne(optional = false)
    public Town getTown() {
        return town;
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

    public void setTown(Town town) {
        this.town = town;
    }
}



