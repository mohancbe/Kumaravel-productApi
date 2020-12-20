package com.ziriusassignment.review.review;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class ReviewApplicationTests {

  String dataPath = "src/test/resources/data.json";

  private String baseUri = "/reviewgroups";

  private String jwtForProduct = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJQcm9kdWN0VXNlciIsImF1dGgiOlt7ImF1dGhvcml0eSI6IlJPTEVfUFJPRFVDVCJ9XSwiaWF0IjoxNjA4MzkxNzIyLCJleHAiOjE2MTcwMzE3MjJ9.v4UF6i7niAroBr7NxhH6rrJquAQhI7P1gpZM4pGR8GE";
  private String jwtForUser = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJVc2VyIiwiYXV0aCI6W3siYXV0aG9yaXR5IjoiUk9MRV9VU0VSIn1dLCJpYXQiOjE2MDgzNTU3MDIsImV4cCI6MTYwODQ0MjEwMn0.uJUDsPpPG2J4Ovx4uU1Tm2yJkMMs_sRAxC5U4xe8sjY";

  private static boolean setUpComplete = false;

  private static JSONObject json = null;

  private static Long reviewGroupId;

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
  public void testReviewGroupCreateReviewGroupWithUserRoleNegative() throws Exception {
    setup();
    MockHttpServletRequestBuilder request = post(baseUri + "?notes=testing_notes").header("Authorization", jwtForUser);
    ResultActions result = mvc.perform(request);
    result.andExpect(status().is4xxClientError()).andReturn();
  }

  @Test
  public void testReviewGroupCreateReviewGroupWithoutAuthNegative() throws Exception {
    setup();
    MockHttpServletRequestBuilder request = post(baseUri + "?notes=testing_notes");
    ResultActions result = mvc.perform(request);
    result.andExpect(status().is4xxClientError()).andReturn();
  }

  @Test
  public void testReviewGroupGetReviewGroupNegative() throws Exception {
    setup();
    MockHttpServletRequestBuilder request = get(baseUri + "/5656");
    ResultActions result = mvc.perform(request);
    result.andExpect(status().is4xxClientError()).andReturn();
  }
  
  @Order(4)
  @Test
  public void testReviewGroup03ZGetReview() throws Exception {
    setup();
    System.out.println("reviewGroupId : " + reviewGroupId);
    MockHttpServletRequestBuilder request = get(baseUri + "/" + reviewGroupId + "/reviews?page=0&size=10");
    ResultActions result = mvc.perform(request);
    MvcResult returns = result.andExpect(status().is2xxSuccessful()).andReturn();
    
    String content = returns.getResponse().getContentAsString();
    JSONObject jsonObject = new JSONObject(content);

    Assert.assertTrue("Success - for get review", jsonObject.getLong("totalRecords") >= 1);
  }
  
  @Order(4)
  @Test
  public void testReviewGroup04ZGetReviewAverageRating() throws Exception {
    setup();
    System.out.println("reviewGroupId : " + reviewGroupId);
    MockHttpServletRequestBuilder request = get(baseUri + "/" + reviewGroupId + "/averagerating");
    ResultActions result = mvc.perform(request);
    MvcResult returns = result.andExpect(status().is2xxSuccessful()).andReturn();
    
    String content = returns.getResponse().getContentAsString();
    JSONObject jsonObject = new JSONObject(content);

    Assert.assertTrue("Success - for get averge review", jsonObject.getLong("averageRating") >= 0);
  }
  
  @Order(3)
  @Test
  public void testReviewGroup03ZAddReview() throws Exception {
    setup();
    System.out.println("reviewGroupId : " + reviewGroupId);
    MockHttpServletRequestBuilder request = post(baseUri + "/" + reviewGroupId + "/reviews")
        .header("Authorization", jwtForUser)
        .content(json.getJSONObject("addReview").getJSONObject("request").toString())
        .contentType(MediaType.APPLICATION_JSON);
    ResultActions result = mvc.perform(request);
    MvcResult returns = result.andExpect(status().is2xxSuccessful()).andReturn();
    
    String content = returns.getResponse().getContentAsString();
    JSONObject jsonObject = new JSONObject(content);

    Assert.assertTrue("Success - for add review", jsonObject.getLong("rate") == 5);
  }

  @Order(2)
  @Test
  public void testReviewGroup02GetReviewGroup() throws Exception {
    setup();
    System.out.println("reviewGroupId : " + reviewGroupId);
    MockHttpServletRequestBuilder request = get(baseUri + "/" + reviewGroupId);
    ResultActions result = mvc.perform(request);
    MvcResult returns = result.andExpect(status().is2xxSuccessful()).andReturn();
    String content = returns.getResponse().getContentAsString();
    JSONObject jsonObject = new JSONObject(content);

    Assert.assertTrue("Success - for get review group", jsonObject.get("notes").equals("testing_notes"));
  }

  @Order(1)
  @Test
  public void testReviewGroup01CreateReviewGroup() throws Exception {
    setup();
    MockHttpServletRequestBuilder request = post(baseUri + "?notes=testing_notes").header("Authorization",
        jwtForProduct);
    ResultActions result = mvc.perform(request);
    MvcResult returns = result.andExpect(status().is2xxSuccessful()).andReturn();
    String content = returns.getResponse().getContentAsString();
    JSONObject jsonObject = new JSONObject(content);

    reviewGroupId = jsonObject.getLong("id");

    Assert.assertTrue("Success - for create review group", jsonObject.get("notes").equals("testing_notes"));
  }

}
