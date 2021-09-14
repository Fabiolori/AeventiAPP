package com.unicam.it.AEventi.Security;

import java.io.Serializable;
//Questa classe si occupa semplicemente di gestire le informazioni sulla richiesta di autenticazione (username e password)
public class  JwtAuthenticationRequest implements Serializable {

  private static final long serialVersionUID = -8445943548965154778L;

  private String username;
  private String password;

  public JwtAuthenticationRequest() {
    super();
  }

  public JwtAuthenticationRequest(String username, String password) {
    this.setUsername(username);
    this.setPassword(password);
  }

  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
