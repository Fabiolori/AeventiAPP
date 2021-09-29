package com.unicam.it.AEventi.Security;

import com.unicam.it.AEventi.Repo.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


// Questa classe si occupa di intercettare ogni richiesta che arriva sul backend per verificare validità del token e
// impostare come autenticata la richiesta arrivata, ricostruendo l’userdetails a partire dei claims contenuti nel token, appunto lo chiamo filter perche mi filtra
// le richieste autentiche dalle altre
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {


  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Value("${jwt.header}")
  private String tokenHeader;

  @Autowired
  private AccountRepository accountRepository;


  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
    //Prendo il token da chi fa la richiesta e vedo se è vuoto
    String authToken = request.getHeader(this.tokenHeader);
    final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
    // TODO VEDI BEARER
    if (header == null || !header.startsWith("Bearer ")) {
      chain.doFilter(request, response);
      return;
    }

    final String token = header.split(" ")[1].trim();
    UserDetails userDetails = jwtTokenUtil.getUserDetails(token);
    if (!jwtTokenUtil.validateToken(token,userDetails)) {
      chain.doFilter(request, response);
      return;
    }

    UsernamePasswordAuthenticationToken
      authentication = new UsernamePasswordAuthenticationToken(
      userDetails, null,
      userDetails == null ?
        List.of() : userDetails.getAuthorities()
    );

    authentication.setDetails(
      new WebAuthenticationDetailsSource().buildDetails(request)
    );

    SecurityContextHolder.getContext().setAuthentication(authentication);
    chain.doFilter(request, response);
  }
}
