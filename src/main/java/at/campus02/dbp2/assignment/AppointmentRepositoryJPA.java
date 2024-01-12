package at.campus02.dbp2.assignment;

import java.time.LocalDateTime;
import java.util.List;

public class AppointmentRepositoryJPA implements AppointmentRepository{
    @Override
    public boolean create(Customer customer) {
        return false;
    }

    @Override
    public Customer read(String email) {
        return null;
    }

    @Override
    public Customer update(Customer customer) {
        return null;
    }

    @Override
    public boolean delete(Customer customer) {
        return false;
    }

    @Override
    public boolean create(Provider provider) {
        return false;
    }

    @Override
    public Provider read(Integer id) {
        return null;
    }

    @Override
    public Provider update(Provider provider) {
        return null;
    }

    @Override
    public boolean delete(Provider provider) {
        return false;
    }

    @Override
    public List<Customer> findCustomersBy(String lastname, String firstname) {
        return null;
    }

    @Override
    public List<Provider> findProvidersBy(ProviderType type, String addressPart) {
        return null;
    }

    @Override
    public List<Appointment> findAppointmentsAt(String addressPart) {
        return null;
    }

    @Override
    public List<Appointment> findAppointments(LocalDateTime from, LocalDateTime to) {
        return null;
    }

    @Override
    public List<Appointment> getAppointmentsFor(Customer customer) {
        return null;
    }

    @Override
    public boolean reserve(Appointment appointment, Customer customer) {
        return false;
    }

    @Override
    public boolean cancel(Appointment appointment, Customer customer) {
        return false;
    }

    @Override
    public void close() {

    }
}
