package com.luxurastyle.luxurastyle.Customer;

import jakarta.persistence.*;

@Entity
@Table(name = "customers")
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "username", nullable = false)
  private String username;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "email", unique = true, nullable = false)
  private String email;

  @Column(name = "admin", nullable = false)
  private Boolean admin;

  public Customer() {

  }

  public Customer(String username, String password, String email) {
    this.username = username;
    this.password = password;
    this.email = email;
    this.admin = false;
  }

  public Customer(String username, String password, String email, Boolean admin) {
    this.username = username;
    this.password = password;
    this.email = email;
    this.admin = admin;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return this.id;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getUsername() {
    return this.username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPassword() {
    return this.password;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getEmail() {
    return this.email;
  }

  public void setAdmin(Boolean admin) {
    this.admin = admin;
  }

  public Boolean getAdmin() {
    return this.admin;
  }
}
