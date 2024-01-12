package at.campus02.dbp2.assignment;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Customer {
    @Id
    private String email;
    private String Firstname;
    private String Lastname;


    public Customer() {
    }

    public Customer(String email) {
    }

    public String getEmail() {
        return email;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String firstname) {
        this.Firstname = firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        this.Lastname = lastname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(getEmail(), customer.getEmail()) && Objects.equals(getFirstname(), customer.getFirstname()) && Objects.equals(getLastname(), customer.getLastname());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail(), getFirstname(), getLastname());
    }
}
