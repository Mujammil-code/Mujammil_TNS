package fooddeliverysystem.services;

import java.util.ArrayList;
import java.util.List;

import fooddeliverysystem.entities.Customer;

public class CustomerService {
    private final List<Customer> customers = new ArrayList<>();

    public void addCustomer(Customer c) {
        customers.add(c);
    }

    public Customer findCustomerById(int id) {
        return customers.stream().filter(c -> c.getUserId() == id).findFirst().orElse(null);
    }

    public List<Customer> getAllCustomers() {
        return customers;
    }
}
