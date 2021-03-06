package com.example.demo.services;
import com.example.demo.models.Country;
import java.util.List;
import reactor.core.publisher.Flux;

public interface ICountryService {
    public String getInit(String text);
    List <Country> getAllCountries();
    public Country findByName(String name);

}