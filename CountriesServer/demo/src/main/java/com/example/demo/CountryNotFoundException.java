package com.example.demo;

public class CountryNotFoundException extends RuntimeException {

  public CountryNotFoundException() {
    super("Could not find countries, request failed");
  }
  public CountryNotFoundException(String name) {
    super("Could not find country " + name);
  }
}