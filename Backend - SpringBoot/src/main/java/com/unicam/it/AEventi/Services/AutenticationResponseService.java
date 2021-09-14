package com.unicam.it.AEventi.Services;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

//
public class AutenticationResponseService {


  private final String username;
  Collection<? extends GrantedAuthority> authorities;

  public AutenticationResponseService(String username, Collection<? extends GrantedAuthority> authorities) {
    this.username = username;
    this.authorities = authorities;
  }

  public String getUsername() {
    return this.username;
  }

  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
    this.authorities = authorities;
  }
}
