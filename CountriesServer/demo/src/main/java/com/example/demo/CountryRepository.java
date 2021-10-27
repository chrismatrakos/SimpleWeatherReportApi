package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CountryRepository extends JpaRepository<Country, String> {
    @Query("SELECT c FROM Country c WHERE c.name = ?1")
    Country findByName(String name);
}