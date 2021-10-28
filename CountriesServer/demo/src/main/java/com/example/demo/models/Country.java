package com.example.demo.models;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;
import javax.persistence.Entity;

public class Country {
 
  public String name;
  public String capital;
  public String alpha2Code;
  public String alpha3Code;
  public String region;

  public Country(String name, String capital,  String alpha2Code, String alpha3Code,  String region) {
    this.name = name;
    this.capital = capital;
    this.alpha2Code = alpha2Code;
    this.alpha3Code = alpha3Code;  
    this.region = region;
  }

  public String getName() {
    return this.name;
  }
  
  public void setName(String name) {
    this.name = name;
  }

  public String getCapital() {
    return this.capital;
  }

  public void setCapital(String capital) {
    this.capital = capital;
  }
  
  public String getAlpha2Code() {
    return this.alpha2Code;
  }
 
  public void setAlpha2Code(String alpha2Code) {
    this.alpha2Code = alpha2Code;
  }

  public String getAlpha3Code() {
    return this.alpha3Code;
  }
 
  public void setAlpha3Code(String alpha3Code) {
    this.alpha3Code = alpha3Code;
  }

  public String getRegion() {
    return this.region;
  }
 
  public void setRegion(String region) {
    this.region = region;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.name, this.capital, this.alpha2Code, this.alpha3Code, this.region);
  }

  @Override
  public String toString() {
    return "Country{ " + "name=" + this.name + "capital=" + this.capital + "alpha2Code=" + this.alpha2Code + "alpha3Code=" + this.alpha3Code + "region=" + this.region +"}";
  }
}