package com.example.demo.services;
import com.example.demo.models.Country;
import java.util.List;

public interface ICountryService {
    // List<Country> findAll();
    public String getInit(String text);
    public String getCountry(String name);

}