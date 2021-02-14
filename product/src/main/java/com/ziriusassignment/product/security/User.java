package com.ziriusassignment.product.security;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class User {

  private Long id;
  private String username;
  private String password;
  private List<Role> roles;
  

}
