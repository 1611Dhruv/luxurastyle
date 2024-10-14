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
    customer.setUsername("Test");
    customer.setPassword("Test123");
    customer.setEmail("test@test.com");
    customer.setAdmin(true);

    Assertions.assertEquals(10000001l, customer.getId());
    Assertions.assertEquals("Test", customer.getUsername());
    Assertions.assertEquals("Test123", customer.getPassword());
    Assertions.assertEquals("test@test.com", customer.getEmail());
    Assertions.assertTrue(customer.getAdmin());
  }

  @Test
  public void testCustomerConstructors() {
    {
      Customer customer = new Customer("Test", "Test123", "test@test.com");

      Assertions.assertEquals("Test", customer.getUsername());
      Assertions.assertEquals("Test123", customer.getPassword());
      Assertions.assertEquals("test@test.com", customer.getEmail());
      Assertions.assertFalse(customer.getAdmin());
    }

    {
      Customer customer =
          new Customer("Test", "Test123", "test@test.com", true);

      Assertions.assertEquals("Test", customer.getUsername());
      Assertions.assertEquals("Test123", customer.getPassword());
      Assertions.assertEquals("test@test.com", customer.getEmail());
      Assertions.assertTrue(customer.getAdmin());
    }
  }
}
