package com.example.demo.controllers;

import com.example.demo.models.Country;
import com.example.demo.repositories.CountryRepository;
import com.example.demo.services.ICountryService;
import java.util.List;
import java.util.Arrays;
import java.util.function.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.data.repository.CrudRepository;


@RestController
public class CountryController {

  @Autowired
  private ICountryService countryService;

  CountryController() {}

  @GetMapping("/hello")
  public String hello(
    @RequestParam(value = "name", defaultValue = "World") String name) {
    return countryService.getInit(name);
  }

  @GetMapping("/countries")
  String getAllCountries() {
    Country[] countries = countryService.getAllCountries();
    return Arrays.toString(countries);
  }

  @GetMapping("/countries/{name}")
  String getCountry(@PathVariable String name) {
    return countryService.findByName(name).toString();
    // .orElseThrow(() -> new CountryNotFoundException(name));
  }
}
