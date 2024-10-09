package com.luxurastyle.luxurastyle.Customer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CustomerTest {
  @Test
  public void testCustomerGettersSetters() {
    Customer customer = new Customer();

    customer.setId(10000001l);
    customer.setName("Test");
    customer.setEmail("test@test.com");
    customer.setPassword("Test123");
    customer.setAdmin(true);

    Assertions.assertEquals(10000001l, customer.getId());
    Assertions.assertEquals("Test", customer.getName());
    Assertions.assertEquals("test@test.com", customer.getEmail());
    Assertions.assertEquals("Test123", customer.getPassword());
    Assertions.assertTrue(customer.getAdmin());
  }
}
