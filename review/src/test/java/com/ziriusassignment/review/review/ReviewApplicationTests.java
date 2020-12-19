package com.ziriusassignment.review.review;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReviewApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = { "classpath:application-dev.properties" })
@AutoConfigureMockMvc
class ReviewApplicationTests {

  String dataPath = "src/test/resources/data.json";

  private String baseUri = "/reviewgroups";

  private String jwtForProduct = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJQcm9kdWN0VXNlciIsImF1dGgiOlt7ImF1dGhvcml0eSI6IlJPTEVfUFJPRFVDVCJ9XSwiaWF0IjoxNjA4MzkxNzIyLCJleHAiOjE2MTcwMzE3MjJ9.v4UF6i7niAroBr7NxhH6rrJquAQhI7P1gpZM4pGR8GE";
  private String jwtForUser = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJVc2VyIiwiYXV0aCI6W3siYXV0aG9yaXR5IjoiUk9MRV9VU0VSIn1dLCJpYXQiOjE2MDgzOTE3ODYsImV4cCI6MTYxNzAzMTc4Nn0.Mi52rpMeY9h_GxvLTu9dQaQW0m1qeuFQfv9qCzGWAqk";

  private boolean setUpComplete = false;

  private static JSONObject json = null;

  @Autowired
  MockMvc mvc;

  public void setup() throws Exception {
    if (!setUpComplete) {
      setUpComplete = true;
      System.out.println("inside setpt. hehe");
      json = new JSONObject(FileUtils.readFileToString(new File(dataPath), "UTF-8"));
    }
  }

  @Test
  public void testCreateReviewGroup() throws Exception {
    setup();
    MockHttpServletRequestBuilder request = post(baseUri + "?notes=testing_notes").header("Authorization", jwtForProduct);
    ResultActions result = mvc.perform(request);
    MvcResult returns =  result.andExpect(status().is2xxSuccessful()).andReturn();
    String content = returns.getResponse().getContentAsString();
    
    Assert.assertTrue("Success - for create review group", content.contains("testing_notes"));
  }

}
