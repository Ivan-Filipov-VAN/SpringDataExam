package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class AgentSeedDto {

    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private String email;
    @Expose
    private String town;

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

    @Size(min = 2)
    public String getTown() {
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

    public void setTown(String town) {
        this.town = town;
    }
}
