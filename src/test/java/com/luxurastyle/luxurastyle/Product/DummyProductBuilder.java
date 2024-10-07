package com.luxurastyle.luxurastyle.Product;

import java.util.List;

public class DummyProductBuilder {
  public static List<Product> getProducts() {
    return List.of(new Product("iPhone", "Apple"), new Product("mac","Apple"), new Product("Think-pad","Lenovo"));

  }
}
