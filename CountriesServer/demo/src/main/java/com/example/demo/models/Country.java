package com.example.demo.models;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;
import javax.persistence.Entity;

public class Country {
 
  private String name;
  private String capital;
  private String[] topLevelDomain;
  private String[] altSpellings;
  private String alpha2Code;
  private String alpha3Code;
  private int[] callingCodes;
  private String region;

  public Country() {}

  public Country(String name, String capital, String[] topLevelDomain, String[] altSpellings,  String alpha2Code, String alpha3Code, int[] callingCodes,  String region) {
    this.name = name;
    this.capital = capital;
    this.topLevelDomain = topLevelDomain;
    this.altSpellings = altSpellings;
    this.alpha2Code = alpha2Code;
    this.alpha3Code = alpha3Code;  
    this.callingCodes = callingCodes;
    this.region = region;
  }

  public String getName() {
    return this.name;
  }
  
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {

    if (this == o)
      return true;
    if (!(o instanceof Country))
      return false;
    Country country = (Country) o;
    return Objects.equals(this.name, country.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.name);
  }

  @Override
  public String toString() {
    return "Country{ " + "name=" + this.name + "}";
  }
}