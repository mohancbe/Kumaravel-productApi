package com.ziriusassignment.product.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetails implements UserDetailsService {

  @Autowired
  private PasswordEncoder passwordEncoder;

  private static List<User> userList = new ArrayList<>();

  static {
    List<Role> productRoles = new ArrayList<>();
    productRoles.add(Role.ROLE_PRODUCT);

    List<Role> userRoles = new ArrayList<>();
    userRoles.add(Role.ROLE_USER);

    userList.add(new User(1L, "ProductUser", "Product@123", productRoles));
    userList.add(new User(1L, "User", "User@123", userRoles));
  }

  @Override
  public UserDetails loadUserByUsername(String username) {
    User user = findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User '" + username + "' not found"));
    return org.springframework.security.core.userdetails.User//
        .withUsername(username)//
        .password(passwordEncoder.encode(user.getPassword()))//
        .authorities(user.getRoles())//
        .accountExpired(false)//
        .accountLocked(false)//
        .credentialsExpired(false)//
        .disabled(false)//
        .build();
  }

  public static Optional<User> findByUsername(String username) {
    return userList.stream().filter(user -> user.getUsername().equals(username)).findFirst();
  }

}
