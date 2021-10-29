package com.example.demo;

public class CountryNotFoundException extends RuntimeException {

  public CountryNotFoundException(String name) {
    super("Could not find country " + name);
  }
}