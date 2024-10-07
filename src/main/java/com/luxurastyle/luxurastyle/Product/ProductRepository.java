package com.luxurastyle.luxurastyle.Product;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
  List<Product> getProductsByName(String name);
  List<Product> getProductsByVendor(String vendor);
  Product updateProduct(int id, Product updatedProduct);
  void deleteProductById(int id);
  void deleteProduct(Product p);
}
