package com.ziriusassignment.product.product;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
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
@TestPropertySource(locations = { "classpath:application.properties" })
@AutoConfigureMockMvc
class ProductApplicationTests {

  String dataPath = "src/test/resources/data.json";

  private String baseUri = "/products";
  private String jwtForProduct = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJQcm9kdWN0VXNlciIsImF1dGgiOlt7ImF1dGhvcml0eSI6IlJPTEVfUFJPRFVDVCJ9XSwiaWF0IjoxNjA4NDY5OTA2LCJleHAiOjE2NDAwMDU5MDZ9.ezSL1sjEW_pHTwQpYQcZP83labc5aeJ0r1qkDj1jczI";
  private String jwtForUser = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJVc2VyIiwiYXV0aCI6W3siYXV0aG9yaXR5IjoiUk9MRV9VU0VSIn1dLCJpYXQiOjE2MDg0Njk5ODAsImV4cCI6MTY0MDAwNTk4MH0.xCVg6RzJOOwITBZEsAFBP2s6YlKiNjRZx8YFln_r76U";
  private boolean setUpComplete = false;
  private static JSONObject json;
  private static Long productId;

  @Autowired
  MockMvc mvc;

  @BeforeEach
  public void setup() throws Exception {
    if (!setUpComplete) {
      json = new JSONObject(FileUtils.readFileToString(new File(dataPath), "UTF-8"));
    }
  }

  @Test
  void testCreateProduct() throws Exception {
    setup();
    MockHttpServletRequestBuilder request = post(baseUri).header("Authorization", jwtForProduct)
        .content(json.getJSONObject("createProduct").getJSONObject("request").toString())
        .contentType(MediaType.APPLICATION_JSON);
    mvc.perform(request);
  }

  @Test
  void testUpdateProduct() throws Exception {
    setup();
    MockHttpServletRequestBuilder request = patch(baseUri + "/" + productId).header("Authorization", jwtForProduct)
        .content(json.getJSONObject("updateProduct").getJSONObject("request").toString())
        .contentType(MediaType.APPLICATION_JSON);
    ResultActions result = mvc.perform(request);
    result.andExpect(status().is4xxClientError()).andReturn();
  }

  @Test
  void testGetProduct() throws Exception {
    setup();
    MockHttpServletRequestBuilder request = get(baseUri + "/" + productId);
    ResultActions result = mvc.perform(request);
    result.andExpect(status().is4xxClientError()).andReturn();
  }

  @Test
  void testAddReview() throws Exception {
    setup();
    MockHttpServletRequestBuilder request = post(baseUri + "/" + productId + "/reviews")
        .header("Authorization", jwtForUser)
        .content(json.getJSONObject("addReview").getJSONObject("request").toString())
        .contentType(MediaType.APPLICATION_JSON);
    ResultActions result = mvc.perform(request);
    result.andExpect(status().is4xxClientError()).andReturn();
  }

  @Test
  void testGetReviews() throws Exception {
    setup();
    MockHttpServletRequestBuilder request = get(baseUri + "/" + productId + "/reviews?page=0&size=10&sortBy=rate,asc");
    ResultActions result = mvc.perform(request);
    result.andExpect(status().is4xxClientError()).andReturn();
  }

}
