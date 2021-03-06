package com.unicam.it.AEventi.Security;

import com.unicam.it.AEventi.Models.Account;
import com.unicam.it.AEventi.Models.Authority;
import com.unicam.it.AEventi.Models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public class JwtUserFromAccount {


    private JwtUserFromAccount() {
    }

    public static User create(Account account) {
      return new User(
        account.getUsername(),
        account.getPassword(),
        mapToGrantedAuthorities(account.getAuthorities())
      );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Authority> authorities) {
      return authorities.stream()
        .map(authority -> new SimpleGrantedAuthority(authority.getName().name()))
        .collect(Collectors.toList());
    }
  }

