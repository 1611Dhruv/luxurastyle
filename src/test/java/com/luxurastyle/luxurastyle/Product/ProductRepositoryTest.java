package com.luxurastyle.luxurastyle.Product;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ProductRepositoryTest {

  @Autowired
  private ProductRepository repo;

  @Test
  void testInsertion() {
    Product p = new Product("dummyProd","dummyOrg");
    this.repo.save(p);
    List<Product> products = this.repo.findAll();
    assertEquals(List.of(p), products);

    Optional<Product> productById = this.repo.findById(1);
    assertTrue(productById.isPresent());
    assertEquals(p, productById.get());
  }

  void testRetrieval() {
  }

}
