package com.example.demo.services;
import com.example.demo.models.Country;
import java.util.List;
import reactor.core.publisher.Flux;

public interface ICountryService {
    List<Country> getAll();
    public String getInit(String text);
    public String findByName(String name);

}