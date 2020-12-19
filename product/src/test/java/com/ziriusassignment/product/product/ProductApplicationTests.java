package com.ziriusassignment.product.product;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.ziriusassignment.product.ProductApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = { "classpath:application-dev.properties" })
@AutoConfigureMockMvc
class ProductApplicationTests {

  String dataPath = "src/test/resources/data.json";

  private String baseUri = "/products";

  private String jwtForProduct = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJQcm9kdWN0VXNlciIsImF1dGgiOlt7ImF1dGhvcml0eSI6IlJPTEVfUFJPRFVDVCJ9XSwiaWF0IjoxNjA4MzkxNzIyLCJleHAiOjE2MTcwMzE3MjJ9.v4UF6i7niAroBr7NxhH6rrJquAQhI7P1gpZM4pGR8GE";
  private String jwtForUser = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJVc2VyIiwiYXV0aCI6W3siYXV0aG9yaXR5IjoiUk9MRV9VU0VSIn1dLCJpYXQiOjE2MDgzOTE3ODYsImV4cCI6MTYxNzAzMTc4Nn0.Mi52rpMeY9h_GxvLTu9dQaQW0m1qeuFQfv9qCzGWAqk";

  private boolean setUpComplete = false;

  private static JSONObject json = null;

  @Autowired
  MockMvc mvc;

  @Before
  public void setup() throws Exception {
    if (!setUpComplete) {
      System.out.println("inside setpt. hehe");
      json = new JSONObject(FileUtils.readFileToString(new File(dataPath), "UTF-8"));
    }
  }

  @Test
  public void testCreateProduct() throws Exception {
    setup();
    MockHttpServletRequestBuilder request = post(baseUri).header("Authorization", jwtForProduct)
        .content(json.getJSONObject("createProduct1").getJSONObject("request").toString())
        .contentType(MediaType.APPLICATION_JSON);
    ResultActions result = mvc.perform(request);
    result.andExpect(status().is2xxSuccessful()).andReturn();
  }

}
