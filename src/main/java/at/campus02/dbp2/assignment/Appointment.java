package at.campus02.dbp2.assignment;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Appointment {
@Id @GeneratedValue
private Integer Id;
@ManyToOne
private Customer customer;
@ManyToOne
private Provider provider;

private LocalDateTime time;


    public Appointment() {
    }

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private final List<Appointment> appointments = new ArrayList<>();

    public Integer getId() {
        return Id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
    this.customer = customer;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getTime(), that.getTime()) && Objects.equals(getCustomer(), that.getCustomer()) && Objects.equals(getProvider(), that.getProvider());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTime(), getCustomer(), getProvider());
    }

}
