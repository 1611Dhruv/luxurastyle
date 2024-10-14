package com.luxurastyle.luxurastyle.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

  @Autowired
  private CustomerRepository customerRepository;

  public Customer createCustomer(Customer customer) {
    return customerRepository.save(customer);
  }

  public Optional<Customer> getCustomerById(Long id) {
    return customerRepository.findById(id);
  }

  public List<Customer> getAllCustomers() {
    return customerRepository.findAll();
  }

  public Optional<Customer> updateCustomer(Long id, Customer details) {
    return customerRepository.findById(id).map(customer -> {
      customer.setUsername(details.getUsername());
      customer.setPassword(details.getPassword());
      customer.setEmail(details.getEmail());
      customer.setAdmin(details.getAdmin());
      return customerRepository.save(customer);
    });
  }

  public boolean deleteCustomer(Long id) {
    return customerRepository.findById(id).map(customer -> {
      customerRepository.delete(customer);
      return true;
    }).orElse(false);
  }
}
