package com.unicam.it.AEventi.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AutenticationService {

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  private AccountService accountService;

  public boolean autenticate(String username, String password) {
    return encoder.matches(password,accountService.loadUserByUsername(username).getPassword());
  }
}
