package com.unicam.it.AEventi.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.List;

//Questa classe permette di cercare gli utenti per autorità oppure di conoscere l'autorità di ogni utente
@Entity
  @Table(name = "authorities")
  public class Authority {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Column(name = "name", length = 50)
    @NotNull
    @Enumerated(EnumType.STRING)
    private Authorities name;

    @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Account> accounts;

    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }

    public Authorities getName() {
      return name;
    }

    public void setName(Authorities name) {
      this.name = name;
    }

    public List<Account> getAccounts() {
      return accounts;
    }

    public void setAccounts(List<Account> accounnts) {
      this.accounts = accounts;
    }
  }
