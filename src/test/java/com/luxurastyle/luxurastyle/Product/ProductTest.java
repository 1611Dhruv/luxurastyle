package com.luxurastyle.luxurastyle.Product;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductTest {
  @Test
  void testSetGetID() {
    Product pdt = new Product();
    pdt.setId(10);
    Assertions.assertEquals(10, pdt.getId(), "The expected id is not equal");
  }
}
