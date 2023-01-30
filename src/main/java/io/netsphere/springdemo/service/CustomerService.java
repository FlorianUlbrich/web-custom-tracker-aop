package io.netsphere.springdemo.service;

import io.netsphere.springdemo.entity.Customer;
import java.util.List;

public interface CustomerService {

    public List<Customer> getCustomers();

    public void saveCustomer(Customer customer);

    public Customer getCustomer(int id);

    public void deleteCustomer(int id);

    public List<Customer> searchCustomer(String name);
}