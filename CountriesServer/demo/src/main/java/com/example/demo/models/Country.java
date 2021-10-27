package com.example.demo.models;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;
import javax.persistence.Entity;

@Entity
public class Country {
 
  private @Id @GeneratedValue Long id;
  private String name;
  private String role;

  public Country() {}

  public Country(String name, String role) {
    this.id = id; 
    this.name = name;
    this.role = role;
  }

  public Long getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public String getRole() {
    return this.role;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setRole(String role) {
    this.role = role;
  }

  @Override
  public boolean equals(Object o) {

    if (this == o)
      return true;
    if (!(o instanceof Country))
      return false;
    Country country = (Country) o;
    return Objects.equals(this.id, country.id) && Objects.equals(this.name, country.name)
        && Objects.equals(this.role, country.role);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.name, this.role);
  }

  @Override
  public String toString() {
    return "Country{" + "id=" + this.id + ", name='" + this.name + '\'' + ", role='" + this.role + '\'' + '}';
  }
}