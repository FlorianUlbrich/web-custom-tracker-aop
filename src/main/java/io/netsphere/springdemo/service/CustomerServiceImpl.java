package io.netsphere.springdemo.service;

import io.netsphere.springdemo.dao.CustomerDaoImpl;
import io.netsphere.springdemo.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    // inject customer dao implementation
    @Autowired
    private CustomerDaoImpl customerDao;

    @Override
    @Transactional
    public List<Customer> getCustomers() {
        return customerDao.getCustomers();
    }

    @Override
    @Transactional
    public void saveCustomer(Customer customer) {
        customerDao.saveCustomer(customer);
    }

    @Override
    @Transactional
    public Customer getCustomer(int id) {
        return customerDao.getCustomer(id);
    }

    @Override
    @Transactional
    public void deleteCustomer(int id) {
        customerDao.deleteCustomer(id);
    }

    @Override
    @Transactional
    public List<Customer> searchCustomer(String name) {
        return customerDao.searchCustomer(name);
    }
}
