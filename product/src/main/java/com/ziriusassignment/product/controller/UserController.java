package com.ziriusassignment.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ziriusassignment.product.exception.InvalidCredentialsException;
import com.ziriusassignment.product.security.JwtTokenProvider;
import com.ziriusassignment.product.security.MyUserDetails;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/users")
@Api(value = "JWT Tokens", tags = "JWT Tokens", description =  "Using this section you can "
    + "obtain JWT token to access the Product and Review APIs")
public class UserController {

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @Autowired
  private AuthenticationManager authenticationManager;

  @PostMapping("/signin")
  @ApiOperation(value = "Using this API, you can obtain"
      + " JWT token to access the Product and Review APIs",
  notes = "`ProductUser/Product@123` to add/update product. `User/User@123` to "
      + "add review for product.", nickname = "login")
  public String login(
      @ApiParam(value = "Username", required = true)
      @RequestParam String username, 
      @ApiParam(value = "Password", required = true)
      @RequestParam String password) {
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
      return "Bearer "
          + jwtTokenProvider.createToken(username, MyUserDetails.findByUsername(username).get().getRoles());
    } catch (AuthenticationException e) {
      throw new InvalidCredentialsException("Invalid username or password");
    }
  }

}
