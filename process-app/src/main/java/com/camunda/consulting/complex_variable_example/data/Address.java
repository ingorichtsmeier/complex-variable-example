package com.camunda.consulting.complex_variable_example.data;

import java.io.Serializable;

public class Address implements Serializable {
  
  private String street;
  private String houseNumber;
  private String city;
  private String postCode;
  private String country;
  
  public Address(String street, String houseNumber, String city, String postCode, String country) {
    super();
    this.street = street;
    this.houseNumber = houseNumber;
    this.city = city;
    this.postCode = postCode;
    this.country = country;
  }

  public Address() {
    super();
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getHouseNumber() {
    return houseNumber;
  }

  public void setHouseNumber(String houseNumber) {
    this.houseNumber = houseNumber;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getPostCode() {
    return postCode;
  }

  public void setPostCode(String postCode) {
    this.postCode = postCode;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  @Override
  public String toString() {
    return String.format("Address [street=%s, houseNumber=%s, city=%s, postCode=%s, country=%s]", street, houseNumber,
        city, postCode, country);
  }
}
