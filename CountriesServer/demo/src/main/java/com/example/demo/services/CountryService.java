package com.example.demo.services;

import com.example.demo.models.Country;
import com.example.demo.repositories.CountryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
public class CountryService implements ICountryService {
  private static final String access_key_query = "?access_key=2f36dbac72a501c4abb690df55f486a7";
  private static final ObjectMapper lenientMapper = new ObjectMapper();

  @Override
  public List<Object> getAll() {
    WebClient client = WebClient.create();
    WebClient.ResponseSpec responseSpec = client
      .get()
      .uri("http://api.countrylayer.com/v2/all"+access_key_query)
      .retrieve();
    String responseBody = responseSpec.bodyToMono(String.class).block();
    System.out.println("test ------------------------- " + responseBody.toString());
    return parseResponseAll(responseBody);
  }

  @Override
  public String getInit(String text) {
    return String.format("Welcome to server %s!", text);
  }

  @Override
  public String findByName(String name) {
    WebClient client = WebClient.create();
    WebClient.ResponseSpec responseSpec = client
      .get()
      .uri("http://api.countrylayer.com/v2/name/" + name + access_key_query)
      .retrieve();

    String responseBody = responseSpec.bodyToMono(String.class).block();
    return String.format("%s!", parseCountryByName(responseBody));
  }

  private String parseCountryByName(String responseBody) {
    System.out.println("response ------- " + responseBody.toString());

    Gson g1 = new Gson();
    Country[] myTypes = g1.fromJson(responseBody.toString(), Country[].class);
    System.out.println("g1 ====== " + g1.toJson(myTypes));
    
    System.out.println("g2 ====== " + myTypes[0].toString());

    ObjectMapper mapper = new ObjectMapper();
    List<Object> countriesList = new ArrayList<>();
    try {
      countriesList =
        Arrays.asList(mapper.readValue(responseBody, Object[].class));
    } catch (Exception e) {
      System.out.println("FAILED");
    }
    System.out.println("list ====== " + countriesList);
    Object c = countriesList.get(0);
    System.out.println("c ======== " + c);

    // Gson g = new Gson();
    // Country country = g.fromJson(c, Country.class);
    // System.out.println(country.name); // John
    Gson g = new Gson();
    Person person = g.fromJson(c.toString(), Person.class);
    System.out.println(person.name); // John
    System.out.println(g.toJson(person)); // {"name":"John"}
    return String.format("%s!", countriesList.get(0).toString());
 
  }

  private List<Object> parseResponseAll(String responseBody) {
    ObjectMapper mapper = new ObjectMapper();
    List<Object> countriesList = new ArrayList<>();
    try {
      countriesList =
        Arrays.asList(mapper.readValue(responseBody, Object[].class));
    } catch (Exception e) {
      System.out.println("FAILED");
    }
    return countriesList;
  }
}

class Person {
  public String name;
  public String capital;

  public Person(String name, String capital) {
  this.name = name;
  this.capital = capital;
    }
}