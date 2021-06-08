package com.unicam.it.AEventi.Models;

import javax.persistence.*;
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    long id;

    @Column(name = "email")
    private String email;
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

    public Account(String email, String password, String name, String surname) {
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
