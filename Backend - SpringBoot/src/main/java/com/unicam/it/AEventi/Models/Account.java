package com.unicam.it.AEventi.Models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "accounts")
public class Account{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    long id;

    @Column(name = "username")
    private String username;
    // LA PASSWORD PER FARE L'ACCESSO
    @Column(name = "password")
    private String password;
    //IL NOME DELL'ACCOUNT
    @Column(name = "name")
    private String name;
    //IL COGNOME DELL ACCOUNT
    @Column(name = "surname")
    private String surname;

    public Account() {

    }

    public Account(String username, String password, String name, String surname) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }



  public String getPassword() {
        return password;
    }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
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

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
