package com.example.demo.services;

import com.example.demo.models.Country;
import com.example.demo.repositories.CountryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CountryService implements ICountryService {

  @Override
  public List<Country> getAll() {
    WebClient client = WebClient.create();
    WebClient.ResponseSpec responseSpec = client
      .get()
      .uri("https://restcountries.com/v3.1/all")
      .retrieve();
    String responseBody = responseSpec.bodyToMono(String.class).block();
    System.out.println(responseBody);
    Country c = new Country("C", "A");
    List<Country> list = new ArrayList<Country>();
    list.add(c);
    return list;
  }

  @Override
  public String getInit(String text) {
    return String.format("Hello %s!", text);
  }

  @Override
  public String findByName(String name) {
    WebClient client = WebClient.create();
    WebClient.ResponseSpec responseSpec = client
      .get()
      .uri("https://restcountries.com/v3.1/name/" + name)
      .retrieve();
    String responseBody = responseSpec.bodyToMono(String.class).block();
    ObjectMapper mapper = new ObjectMapper();
    List<Object> slist = new ArrayList<>();

    try
    {
     slist = Arrays.asList(mapper.readValue(responseBody, Object[].class));
    }
    catch(Exception e){System.out.println("FAILED");}
    System.out.println(slist);

    return String.format("Hello %s!", responseBody);
  }
}
