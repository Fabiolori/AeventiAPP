package com.unicam.it.AEventi.Models;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "accounts")
public class Account{


  @Id
  @Column(name = "username", length = 50, unique = true,nullable = false)
  @NotNull
  private String username;

  @Column(name = "password")
  @NotNull
  private String password;

  @Column(name = "name")
  @NotNull
  private String name;

  @Column(name = "surname")
  @NotNull
  private String surname;

  @Column(name = "enabled")
  @NotNull
  private Boolean enabled;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
    name = "accounts_authorities",
    joinColumns = {@JoinColumn(name = "accounts_username", referencedColumnName = "username")},
    inverseJoinColumns = {@JoinColumn(name = "authorities_id", referencedColumnName = "id")})
  private List<Authority> authorities;


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public Boolean getEnabled() {
    return enabled;
  }

  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }

  public List<Authority> getAuthorities() {
    return authorities;
  }

  public void setAuthorities(List<Authority> authorities) {
    this.authorities = authorities;
  }

  @Override
  public String toString() {
    return "Account{" +
      "username='" + username + '\'' +
      ", password='" + password + '\'' +
      ", name='" + name + '\'' +
      ", surname='" + surname + '\'' +
      ", enabled=" + enabled +
      ", authorities=" + authorities +
      '}';
  }

}
