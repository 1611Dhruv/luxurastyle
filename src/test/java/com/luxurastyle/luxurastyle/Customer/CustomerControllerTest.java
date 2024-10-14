package com.luxurastyle.luxurastyle.Customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CustomerControllerTest {

  @InjectMocks
  private CustomerController customerController;

  @Mock
  private CustomerService customerService;

  private Customer customer;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    customer = new Customer();
    customer.setId(10000001L);
    customer.setUsername("Test");
  }

  @Test
  void testCreateCustomer() {
    when(customerService.createCustomer(any(Customer.class))).thenReturn(customer);

    ResponseEntity<Customer> response = customerController.createCustomer(customer);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(customer, response.getBody());

    verify(customerService, times(1)).createCustomer(any(Customer.class));
  }

  @Test
  void testGetCustomerByIdWhenExists() {
    when(customerService.getCustomerById(10000001L)).thenReturn(Optional.of(customer));

    ResponseEntity<Customer> response = customerController.getCustomerById(10000001L);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(customer, response.getBody());

    verify(customerService, times(1)).getCustomerById(10000001L);
  }

  @Test
  void testGetCustomerByIdWhenNotExists() {
    when(customerService.getCustomerById(10000002L)).thenReturn(Optional.empty());

    ResponseEntity<Customer> response = customerController.getCustomerById(10000002L);

    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    verify(customerService, times(1)).getCustomerById(10000002L);
  }

  @Test
  void testGetAllCustomers() {
    List<Customer> customers = Arrays.asList(customer);
    when(customerService.getAllCustomers()).thenReturn(customers);

    ResponseEntity<List<Customer>> response = customerController.getAllCustomers();

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(customers, response.getBody());

    verify(customerService, times(1)).getAllCustomers();
  }

  @Test
  void testUpdateCustomerWhenExists() {
    Customer updatedCustomer = new Customer();
    updatedCustomer.setId(10000001L);
    updatedCustomer.setUsername("Updated Test");

    when(customerService.updateCustomer(eq(10000001L), any(Customer.class)))
        .thenReturn(Optional.of(updatedCustomer));

    ResponseEntity<Customer> response = customerController
        .updateCustomer(10000001L, updatedCustomer);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(updatedCustomer, response.getBody());

    verify(customerService, times(1))
        .updateCustomer(eq(10000001L), any(Customer.class));
  }

  @Test
  void testUpdateCustomerWhenNotExists() {
    when(customerService.updateCustomer(eq(10000002L), any(Customer.class)))
        .thenReturn(Optional.empty());

    ResponseEntity<Customer> response = customerController.updateCustomer(10000002L, customer);

    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    verify(customerService, times(1))
        .updateCustomer(eq(10000002L), any(Customer.class));
  }

  @Test
  void testDeleteCustomerWhenExists() {
    when(customerService.deleteCustomer(10000001L)).thenReturn(true);

    ResponseEntity<Void> response = customerController.deleteCustomer(10000001L);

    assertEquals(HttpStatus.OK, response.getStatusCode());

    verify(customerService, times(1)).deleteCustomer(10000001L);
  }

  @Test
  void testDeleteCustomerWhenNotExists() {
    when(customerService.deleteCustomer(10000002L)).thenReturn(false);

    ResponseEntity<?> response = customerController.deleteCustomer(10000002L);

    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    verify(customerService, times(1)).deleteCustomer(10000002L);
  }
}
