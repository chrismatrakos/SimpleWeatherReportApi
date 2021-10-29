package com.example.demo.controllers;
import com.example.demo.models.Country;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.ResultMatcher;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@AutoConfigureMockMvc

public class CountryControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void hello() throws Exception {
		mvc.perform(
			MockMvcRequestBuilders.get("/hello"))
				.andExpectAll(
					status().isOk(),
				 content().string(equalTo("Welcome to the server World!"))
				);
	}

	@Test
	public void getCountry() throws Exception {
		Country c = new Country();
		c.setName("Spain");
		c.setCapital("Madrid");

		mvc.perform(
			MockMvcRequestBuilders.get("/countries/Spain"))
				.andExpectAll(
					status().isOk(),
			  jsonPath("$.name", is(c.getName())),
					jsonPath("$.capital", is(c.getCapital()))
					);
							
		mvc.perform(
			MockMvcRequestBuilders.get("/countries/invalid"))
				.andExpectAll(
				 content().string(equalTo("Could not find country invalid"))
					);
	}
}