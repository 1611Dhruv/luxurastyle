package com.luxurastyle.luxurastyle.Customer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CustomerRepositoryTest {

  @Autowired
  private CustomerRepository customerRepository;

  @Test
  void testSaveCustomer() {
    Customer customer = new Customer();
    customer.setUsername("Test");
    customer.setPassword("Test123");
    customer.setEmail("test@test.com");
    customer.setAdmin(false);

    Customer savedCustomer = customerRepository.save(customer);

    assertNotNull(savedCustomer.getId());
    assertEquals("Test", savedCustomer.getUsername());
    assertEquals("Test123", savedCustomer.getPassword());
    assertEquals("test@test.com", savedCustomer.getEmail());
    assertFalse(savedCustomer.getAdmin());
  }

  @Test
  void testFindByIdWhenExists() {
    Customer customer = new Customer();
    customer.setUsername("Test");
    customer.setPassword("Test123");
    customer.setEmail("test@test.com");
    customer.setAdmin(false);

    Customer savedCustomer = customerRepository.save(customer);

    Optional<Customer> foundCustomer = customerRepository.findById(savedCustomer.getId());

    assertTrue(foundCustomer.isPresent());
    assertEquals(savedCustomer.getUsername(), foundCustomer.get().getUsername());
  }

  @Test
  void testFindByIdWhenNotExists() {
    Optional<Customer> foundCustomer = customerRepository.findById(10000002L);

    assertTrue(foundCustomer.isEmpty());
  }

  @Test
  void testDeleteCustomer() {
    Customer customer = new Customer();
    customer.setUsername("Test");
    customer.setPassword("Test123");
    customer.setEmail("test@test.com");
    customer.setAdmin(false);

    Customer savedCustomer = customerRepository.save(customer);

    customerRepository.delete(savedCustomer);
    Optional<Customer> deletedCustomer = customerRepository.findById(savedCustomer.getId());

    assertTrue(deletedCustomer.isEmpty());
  }

  @Test
  void testUpdateCustomer() {
    Customer customer = new Customer();
    customer.setUsername("Test");
    customer.setPassword("Test123");
    customer.setEmail("test@test.com");
    customer.setAdmin(false);

    Customer savedCustomer = customerRepository.save(customer);

    savedCustomer.setUsername("UpdatedTest");
    savedCustomer.setPassword("Test246");
    savedCustomer.setEmail("test@test.update");
    savedCustomer.setAdmin(true);
    Customer updatedCustomer = customerRepository.save(savedCustomer);

    assertEquals("UpdatedTest", updatedCustomer.getUsername());
    assertEquals("Test246", updatedCustomer.getPassword());
    assertEquals("test@test.update", updatedCustomer.getEmail());
    assertTrue(updatedCustomer.getAdmin());
  }

  @Test
  void testFindAll() {
    Customer customer1 = new Customer();
    customer1.setUsername("Test1");
    customer1.setPassword("Test123");
    customer1.setEmail("test1@test.com");
    customer1.setAdmin(false);

    Customer customer2 = new Customer();
    customer2.setUsername("Test2");
    customer2.setPassword("Test234");
    customer2.setEmail("test2@test.com");
    customer2.setAdmin(true);

    customerRepository.save(customer1);
    customerRepository.save(customer2);

    List<Customer> allCustomers = customerRepository.findAll();

    assertEquals(2, allCustomers.size());
    assertEquals("Test1", allCustomers.get(0).getUsername());
    assertEquals("Test2", allCustomers.get(1).getUsername());
  }
}
