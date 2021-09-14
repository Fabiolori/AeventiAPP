package com.unicam.it.AEventi.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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


  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
    String authToken = request.getHeader(this.tokenHeader);

    UserDetails userDetails = null;

    if(authToken != null){
      userDetails = jwtTokenUtil.getUserDetails(authToken);
    }

    if (userDetails != null && SecurityContextHolder.getContext().getAuthentication() == null) {

      // Ricostruisco gli userdetails con le informazioni che trovo nel token e poi
      // controllo l'integrita' del token
      if (jwtTokenUtil.validateToken(authToken, userDetails)) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    }

    chain.doFilter(request, response);
  }
}
