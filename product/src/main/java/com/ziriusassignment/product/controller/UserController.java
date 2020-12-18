package com.ziriusassignment.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ziriusassignment.product.security.JwtTokenProvider;
import com.ziriusassignment.product.security.MyUserDetails;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @Autowired
  private AuthenticationManager authenticationManager;

  @PostMapping("/signin")
  public String login(@RequestParam String username, @RequestParam String password) {
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
      return "Bearer " + jwtTokenProvider.createToken(username, MyUserDetails.findByUsername(username).get().getRoles());
    } catch (AuthenticationException e) {
      throw new RuntimeException("Invalid username/password supplied");
    }
  }

}
