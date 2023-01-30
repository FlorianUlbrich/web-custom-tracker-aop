package io.netsphere.springdemo.dao;

import io.netsphere.springdemo.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

// @Repository lets spring register DAOImpl  and inherits from @Component
// enables auto scanning an component scanning
// spring provides translation of any JDBC related exception
@Repository
public class CustomerDaoImpl implements CustomerDAO {

    // injection of the session factory
    // with spring 4.3 and newer @autowired can be omitted, if class has a constructor
    // try to remove it later
    @Autowired
    private SessionFactory factory;

    // @Transactional automatically start and closes the transaction, got moved to service
    @Override
    public List<Customer> getCustomers() {

        // get current hibernate session
        Session session = factory.getCurrentSession();

        // create a query
        Query<Customer> query = session.createQuery("from Customer order by lastName", Customer.class);

        // return list of customers
        return query.getResultList();
    }

    @Override
    public void saveCustomer(Customer customer) {

        // get current hibernate session
        Session session = factory.getCurrentSession();

        // save customer
        session.saveOrUpdate(customer);
    }

    @Override
    public Customer getCustomer(int id) {

        // get current hibernate session
        Session session = factory.getCurrentSession();

        // get customer with given id from database
        return session.get(Customer.class, id);
    }

    @Override
    public void deleteCustomer(int id) {

        // get current hibernate session
        Session session = factory.getCurrentSession();

        // delete customer with primary key
        Query query = session.createQuery("delete from Customer where id=:customerId");
        query.setParameter("customerId", id);

        // works for update and delete
        query.executeUpdate();
    }

    @Override
    public List<Customer> searchCustomer(String name) {

        // get current hibernate session
        Session session = factory.getCurrentSession();

        // make query
        Query<Customer> query = session.createQuery("from Customer where lastName=:name");
        query.setParameter("name", name);

        return query.getResultList();
    }
}
