package com.example.demo.services;
import com.example.demo.models.Country;
import java.util.List;
import java.util.ArrayList;
import com.example.demo.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryService implements ICountryService {
   
   // @Bean 
   //  public WebClient localApiClient() {
   //     return WebClient.create("https://restcountries.com/v3.1/all");
   //  }
   //  private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(3);
   //  private final WebClient localApiClient;
   //  @Autowired
   //  public CountryService(WebClient localApiClient) {
   //      this.localApiClient = localApiClient;
   //  }
   //  @Autowired
   //  private RestTemplate restTemplate;
    
    @Override
    public List<Country> getAll() {
      final String uri = "https://restcountries.com/v3.1/all";

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
    public String getCountry(String name){
     	return String.format("Hello %s!", name);
    }
    
}