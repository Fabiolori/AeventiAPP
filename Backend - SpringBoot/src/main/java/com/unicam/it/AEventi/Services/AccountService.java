package com.unicam.it.AEventi.Services;

import com.unicam.it.AEventi.Models.Account;
import com.unicam.it.AEventi.Models.User;
import com.unicam.it.AEventi.Repo.AccountRepository;
import com.unicam.it.AEventi.Security.JwtUserFromAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service

public class AccountService implements UserDetailsService {
  @Autowired
  PasswordEncoder encoder;

  @Autowired
  private AccountRepository accountRepository;

  @Override
  public User loadUserByUsername(String username) throws UsernameNotFoundException {
    Account account = accountRepository.findByUsername(username);

    if (account == null) {
      throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
    } else {
      return JwtUserFromAccount.create(account);
    }

  }

  public Account createAccount(Account account) {
    account.setPassword(encoder.encode(account.getPassword()));
    return accountRepository.save(account);
  }

  public Account updateAccount(Account newAccount, String username) {
      Account account = accountRepository.findByUsername(username);
          newAccount.setUsername(username);
          account.setName(newAccount.getName());
          account.setPassword(newAccount.getPassword());
          account.setSurname(newAccount.getSurname());
          account.setEnabled(newAccount.getEnabled());
          account.setAuthorities(newAccount.getAuthorities());
          return accountRepository.save(account);
        }

  public void deleteAccount(String username) {

    accountRepository.delete(accountRepository.findByUsername(username));

    }
  }



