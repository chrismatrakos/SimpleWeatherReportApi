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

  public String getCapital() {
    return this.capital;
  }

  public void setCapital(String capital) {
    this.capital = capital;
  }

  public String[] getTopLevelDomain() {
    this.topLevelDomain = topLevelDomain;
  }

  public void setTopLevelDomain(String[] topLevelDomain) {
    this.capital = topLevelDomain;
  }

  public String[] getAltSpellings() {
    return this.altSpellings;
  }
 
  public void setAltSpellings(String [] altSpellings) {
    this.altSpellings = altSpellings;
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

  public int[] getCallingCodes() {
    return this.callingCodes;
  }
 
  public void setCallingCodes(int[] callingCodes) {
    this.callingCodes = callingCodes;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof Country))
      return false;
    Country country = (Country) o;
    return Objects.equals(this.name, country.name) &&
      Objects.equals(this.capital, country.capital) &&
      Objects.equals(this.topLevelDomain, country.topLevelDomain) &&
      Objects.equals(this.altSpellings, country.altSpellings) &&
      Objects.equals(this.alpha2Code, country.alpha2Code) &&
      Objects.equals(this.alpha3Code, country.alpha3Code) &&
      Objects.equals(this.callingCodes, country.callingCodes) &&
      Objects.equals(this.region, country.region);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.name, this.capital,this.topLevelDomain, this.altSpellings, this.alpha2Code,this.alpha3Code, this.callingCodes, this.region);
  }

  @Override
  public String toString() {
    return "Country{ " + "name=" + this.name + "capital=" + this.capital + + "topLevelDomain=" + this.topLevelDomain + "altSpellings=" + this.altSpellings + "alpha2Code=" + this.alpha2Code + "alpha3Code=" + this.alpha3Code + "callingCodes=" + this.callingCodes + "region=" + this.region +"}";
  }
}