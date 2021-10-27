package com.example.demo.services;
import com.example.demo.models.Country;
import com.example.demo.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService implements ICountryService {

    // @Autowired
    // private CountryRepository repository;

    // @Override
    // public List<Country> findAll() {
    //   return (List<Country>) repository.findAll();
    // }
    
    @Override
    public String getInit(String text){
     	return String.format("Hello %s!", text);
    }

    @Override
    public String getCountry(String name){
     	return String.format("Hello %s!", name);
    }
}