package com.luxurastyle.luxurastyle.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

  @Autowired
  private CustomerService customerService;

  @PostMapping
  public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
    Customer savedCustomer = customerService.createCustomer(customer);
    return ResponseEntity.ok(savedCustomer);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
    Optional<Customer> customer = customerService.getCustomerById(id);
    return customer.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping
  public ResponseEntity<List<Customer>> getAllCustomers() {
    List<Customer> customers = customerService.getAllCustomers();
    return ResponseEntity.ok(customers);
  }

  @PostMapping("/{id}")
  public ResponseEntity<Customer> updateCustomer
      (@PathVariable Long id, @RequestBody Customer customerDetails) {
    Optional<Customer> updatedCustomer = customerService.updateCustomer(id, customerDetails);
    return updatedCustomer.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
    boolean isDeleted = customerService.deleteCustomer(id);
    if (isDeleted)
      return ResponseEntity.ok().build();
    return ResponseEntity.notFound().build();
  }
}
