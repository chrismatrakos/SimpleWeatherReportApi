package com.example.demo.services;
import com.example.demo.CountryNotFoundException;
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
import org.springframework.http.HttpStatus;
import org.springframework.core.*;


@Service
public class CountryService implements ICountryService {
  private static final String access_key_query = "?access_key=2f36dbac72a501c4abb690df55f486a7";
  private static final ObjectMapper lenientMapper = new ObjectMapper();

  @Override
  public String getInit(String text) {
    return String.format("Welcome to the server %s!", text);
  }

  @Override
  public List<Country> getAllCountries() {
    WebClient client = WebClient.create();
    List <Country> countries = client.get()
      .uri("http://api.countrylayer.com/v2/all" + access_key_query )
      .retrieve()
      .onStatus(httpStatus -> !HttpStatus.OK.equals(httpStatus),
        clientResponse -> {return Mono.error(new CountryNotFoundException());})
      .bodyToMono(new ParameterizedTypeReference<List<Country>>() {})
      .onErrorMap(throwable ->{
        return new CountryNotFoundException();
      })
      .block();
    return countries;
  }

  @Override
  public Country findByName(String name) {
    WebClient client = WebClient.create();
    Country[] country = client.get()
      .uri("http://api.countrylayer.com/v2/name/" + name + access_key_query)
      .retrieve()
      .onStatus(httpStatus -> !HttpStatus.OK.equals(httpStatus),
                clientResponse -> {return Mono.error(new CountryNotFoundException(name));})
      .bodyToMono(Country[].class)
      .onErrorMap(throwable ->{
        return new CountryNotFoundException(name);
      })
      .block();
    
    System.out.println(country[0].toString());
    return country[0];
  }
}
