package com.unicam.it.AEventi.Repo;

import com.unicam.it.AEventi.Models.Account;
import com.unicam.it.AEventi.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
  Account findByUsername(String username);

}

