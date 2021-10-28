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

@Service
public class CountryService implements ICountryService {
  private static final String access_key_query = "?access_key=2f36dbac72a501c4abb690df55f486a7";
  private static final ObjectMapper lenientMapper = new ObjectMapper();

  @Override
  public List<Country> getAll() {
    WebClient client = WebClient.create();
    WebClient.ResponseSpec responseSpec = client
      .get()
      .uri("http://api.countrylayer.com/v2/all"+access_key_query)
      .retrieve();
    String responseBody = responseSpec.bodyToMono(String.class).block();
    System.out.println(responseBody);
    parseResponse(responseBody);
    return new ArrayList<>();
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
    ObjectMapper mapper = new ObjectMapper();
    List<Object> countriesList = new ArrayList<>();
    try {
      countriesList =
        Arrays.asList(mapper.readValue(responseBody, Object[].class));
    } catch (Exception e) {
      System.out.println("FAILED");
    }

    return String.format("%s!", countriesList.get(0).toString());
    // Country c = lenientMapper.readValue(slist.get(0), Country.class);
  }

  private List<Country> parseResponse(String responseBody) {
    ObjectMapper mapper = new ObjectMapper();
    List<Object> countriesList = new ArrayList<>();
    try {
      countriesList =
        Arrays.asList(mapper.readValue(responseBody, Object[].class));
    } catch (Exception e) {
      System.out.println("FAILED");
    }
    return new ArrayList<>();
  }
}
