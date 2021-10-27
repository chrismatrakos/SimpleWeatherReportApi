package com.example.demo;

import java.util.List;
import java.util.*;
import java.util.function.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
class CountryController {

  // private CountryRepository repository;
  
  // CountryController(CountryRepository repository) {
  //   this.repository = repository;
  // }

	 @GetMapping("/hello")
		public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
				return String.format("Hello %s!", name);
		}
  
  // @GetMapping("/countries")
  // List<Country> all() {
  //  return String.format("Hello");
  //   // return repository.findAll();
  // }
  
  // @GetMapping("/countries/{name}")
  // Country one(@PathVariable String name) {
    
  //   return repository.findByName(name)
  //     .orElseThrow(() -> new CountryNotFoundException(name));
  // }
}