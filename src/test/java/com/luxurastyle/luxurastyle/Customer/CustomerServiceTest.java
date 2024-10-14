package com.luxurastyle.luxurastyle.Customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

  @InjectMocks
  private CustomerService customerService;

  @Mock
  private CustomerRepository customerRepository;

  private Customer customer;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    customer = new Customer();
    customer.setId(10000001L);
    customer.setUsername("Test");
    customer.setPassword("Test123");
    customer.setEmail("test@test.com");
    customer.setAdmin(false);
  }

  @Test
  void testCreateCustomer() {
    when(customerRepository.save(any(Customer.class))).thenReturn(customer);

    Customer savedCustomer = customerService.createCustomer(customer);

    assertEquals(customer, savedCustomer);

    verify(customerRepository, times(1)).save(customer);
  }

  @Test
  void testGetCustomerByIdWhenExists() {
    when(customerRepository.findById(10000001L)).thenReturn(Optional.of(customer));

    Optional<Customer> foundCustomer = customerService.getCustomerById(10000001L);

    assertTrue(foundCustomer.isPresent());
    assertEquals(customer, foundCustomer.get());

    verify(customerRepository, times(1)).findById(10000001L);
  }

  @Test
  void testGetCustomerByIdWhenNotExists() {
    when(customerRepository.findById(10000002L)).thenReturn(Optional.empty());

    Optional<Customer> foundCustomer = customerService.getCustomerById(10000002L);

    assertTrue(foundCustomer.isEmpty());

    verify(customerRepository, times(1)).findById(10000002L);
  }

  @Test
  void testGetAllCustomers() {
    List<Customer> customers = Arrays.asList(customer);
    when(customerRepository.findAll()).thenReturn(customers);

    List<Customer> allCustomers = customerService.getAllCustomers();

    assertEquals(customers, allCustomers);

    verify(customerRepository, times(1)).findAll();
  }

  @Test
  void testUpdateCustomerWhenExists() {
    Customer details = new Customer();
    details.setUsername("UpdatedTest");
    details.setPassword("Test246");
    details.setEmail("test@test.update");
    details.setAdmin(true);

    when(customerRepository.findById(10000001L)).thenReturn(Optional.of(customer));
    when(customerRepository.save(any(Customer.class))).thenReturn(customer);

    Optional<Customer> updatedCustomer = customerService.updateCustomer(10000001L, details);

    assertTrue(updatedCustomer.isPresent());
    assertEquals("UpdatedTest", updatedCustomer.get().getUsername());
    assertEquals("Test246", updatedCustomer.get().getPassword());
    assertEquals("test@test.update", updatedCustomer.get().getEmail());
    assertEquals(true, updatedCustomer.get().getAdmin());

    verify(customerRepository, times(1)).findById(10000001L);
    verify(customerRepository, times(1)).save(customer);
  }

  @Test
  void testUpdateCustomerWhenNotExists() {
    Customer details = new Customer();
    details.setUsername("UpdatedTest");

    when(customerRepository.findById(10000002L)).thenReturn(Optional.empty());

    Optional<Customer> updatedCustomer = customerService.updateCustomer(10000002L, details);

    assertTrue(updatedCustomer.isEmpty());

    verify(customerRepository, times(1)).findById(10000002L);
    verify(customerRepository, times(0)).save(any(Customer.class));
  }

  @Test
  void testDeleteCustomerWhenExists() {
    when(customerRepository.findById(10000001L)).thenReturn(Optional.of(customer));

    boolean isDeleted = customerService.deleteCustomer(10000001L);

    assertTrue(isDeleted);

    verify(customerRepository, times(1)).findById(10000001L);
    verify(customerRepository, times(1)).delete(customer);
  }

  @Test
  void testDeleteCustomerWhenNotExists() {
    when(customerRepository.findById(10000002L)).thenReturn(Optional.empty());

    boolean isDeleted = customerService.deleteCustomer(10000002L);

    assertTrue(!isDeleted);

    verify(customerRepository, times(1)).findById(10000002L);
    verify(customerRepository, times(0)).delete(any(Customer.class));
  }
}
