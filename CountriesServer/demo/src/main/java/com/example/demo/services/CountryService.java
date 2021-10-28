package com.example.demo.services;
import com.example.demo.models.Country;
import java.util.List;
import java.util.ArrayList;
import com.example.demo.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CountryService implements ICountryService {
    // private final WebClient webClient;

    // @Autowired
    // public CountryService(WebClient.Builder webClientBuilder) {
		  //  this.webClient = webClientBuilder.baseUrl("https://restcountries.com/v3.1").build();
	   // }
    
    @Override
    public List<Country> getAll() {
     WebClient client = WebClient.create();
     WebClient.ResponseSpec responseSpec = client.get().uri("https://restcountries.com/v3.1/all").retrieve();
     String responseBody = responseSpec.bodyToMono(String.class).block();
     System.out.println(responseBody);
      // return this.webClient.get().uri("/all")
						// .retrieve();
      // .bodyToMono(Country.class);
      // WebClient webClient = WebClient.create();
      Country c = new Country("C", "A");
      List<Country> list = new ArrayList<Country>();
      list.add(c);
      return list;
    }
    
    @Override
    public String getInit(String text){
     	return String.format("Hello %s!", text);
    }

    @Override
    public String findByName(String name){
     WebClient client = WebClient.create();
     WebClient.ResponseSpec responseSpec = client.get().uri("https://restcountries.com/v3.1/name/" +name ).retrieve();
     String responseBody = responseSpec.bodyToMono(String.class).block();
     
     System.out.println(responseBody);
     
     return String.format("Hello %s!", name);
    }
}