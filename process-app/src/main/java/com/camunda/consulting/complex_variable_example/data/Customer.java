package com.camunda.consulting.complex_variable_example.data;

import java.io.Serializable;
import java.time.LocalDate;

public class Customer implements Serializable {
  
  private String firstName;
  private String lastName;
  private Address address;
  private LocalDate birthday;
  
  public Customer(String firstName, String lastName, Address address, LocalDate birthday) {
    super();
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.birthday = birthday;
  }

  public Customer() {
    super();
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public LocalDate getBirthday() {
    return birthday;
  }

  public void setBirthday(LocalDate birthday) {
    this.birthday = birthday;
  }

  @Override
  public String toString() {
    return String.format("Customer [firstName=%s, lastName=%s, address=%s, birthday=%s]", firstName, lastName, address,
        birthday);
  }

}
