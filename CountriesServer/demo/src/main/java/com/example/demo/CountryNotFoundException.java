package com.example.demo;

class CountryNotFoundException extends RuntimeException {

  CountryNotFoundException(String name) {
    super("Could not find country " + name);
  }
}