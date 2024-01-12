package at.campus02.dbp2.assignment;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class AppointmentRepositoryFactory implements AppointmentRepository{

    private final EntityManager manager;

    public static AppointmentRepositoryFactory repositoryFactory;
    // Für Klassen, die nur statische Methoden anbieten, verwendet man in Java häufig einen
    // privaten Konstruktor, damit von "außen" keine Instanz erzeugt werden kann.
    private AppointmentRepositoryFactory(EntityManagerFactory factory) {
        manager = factory.createEntityManager();
    }

    /**
     * Factory Methode, mit deren Hilfe eine Instanz der Implementierung von <code>{@link AppointmentRepository}</code>
     * erzeugt wird.
     * @param factory EntityManagerFactory zur Erzeugung des EntityManagers, den die Implementierung für den
     *                Zugriff auf die DB verwenden soll.
     * @return eine Implementierung von <code>{@link AppointmentRepository}</code>.
     */
    public static AppointmentRepository get(EntityManagerFactory factory) {
        return repositoryFactory = new AppointmentRepositoryFactory(factory);
    }

    @Override
    public boolean create(Customer customer) {
        if (customer == null)
            return false;
        if (customer.getEmail()== null)
            return false;
        if (read(customer.getEmail()) != null){
            return false;
        }
        manager.getTransaction().begin();
        manager.persist(customer);
        manager.getTransaction().commit();
        return true;
    }

    @Override
    public Customer read(String email) {
        if (email == null){
            return null;
        } return manager.find(Customer.class,email);
    }

    @Override
    public Customer update(Customer customer){
        if (customer == null)
            return null;
        if (read(customer.getEmail()) == null) {
        throw new IllegalArgumentException("Customer does not exist, cannot update!");
    }
        manager.getTransaction().begin();
    Customer managed = manager.merge(customer);
        manager.getTransaction().commit();
        return managed;
}
    @Override
    public boolean delete(Customer customer) {
        if (customer == null)
            return false;
        if (read(customer.getEmail()) == null){
            throw new IllegalArgumentException("Customer does not exist, cannot delete!");
        }
        manager.getTransaction().begin();
        manager.remove(manager.merge(customer));
        manager.getTransaction().commit();
        return true;
    }


    @Override
    public boolean create(Provider provider) {
        if (provider == null)
            return false;
        if (provider.getId() != null){
            return false;
        }
        manager.getTransaction().begin();
        manager.persist(provider);
        manager.getTransaction().commit();
        return true;
    }

    @Override
    public Provider read(Integer id) {
        if (id == null){
            return null;
        } return manager.find(Provider.class,id);
    }


    @Override
    public Provider update(Provider provider) {
     /*   if (provider == null)
            return null;
            if (read(provider.getId())== null){
               throw new IllegalArgumentException("Customer does not exist, cannot update!");
            }
            manager.getTransaction().begin();
            Provider managed = manager.merge(provider);
            manager.getTransaction().commit();
            return managed;

        }

       */
        Provider existingProvider = read(provider.getId());

        List<Appointment> existingAppointments = existingProvider.getAppointments();

// Remove appointments no longer associated with the provider
        List<Appointment> appointmentsToRemove = new ArrayList<>();
        for (Appointment appointment : existingAppointments) {
            if (!provider.getAppointments().contains(appointment)) {
                appointmentsToRemove.add(appointment);}
        }
        return existingProvider;
    }

    @Override
    public boolean delete(Provider provider) {
        if (provider == null)
            return false;
        if (read(provider.getId()) == null){
            throw new IllegalArgumentException("Provider does not exist, cannot delete!");
        }
        manager.getTransaction().begin();
        manager.remove(manager.merge(provider));
        manager.getTransaction().commit();
        return true;
    }

    @Override
    public List<Customer> findCustomersBy(String lastname, String firstname) {
        if (lastname == null || lastname.isEmpty() && firstname == null || firstname.isEmpty()){
            return Collections.emptyList();
        }
        TypedQuery<Customer> query = manager.createNamedQuery(
                "Customer.findByLastname",
                Customer.class
        );
        query.setParameter("lastnamePart", "%" + lastname + "%");
        return query.getResultList();
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
