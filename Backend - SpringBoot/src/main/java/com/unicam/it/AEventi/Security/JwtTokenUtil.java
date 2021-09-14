package com.unicam.it.AEventi.Security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unicam.it.AEventi.Models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;



  @Component
  public class JwtTokenUtil {
    @Value("${jwt.secret}")
    private String secret;
    @Autowired
    ObjectMapper objectMapper;
    @Value("${jwt.expiration}")
    private Long expiration;

    public String getUsernameFromToken(String token) {
      return getClaimFromToken(token, Claims::getSubject);
    }

    public User getUserDetails(String token) {
      if(token == null){
        return null;
      }
      try {
        final Claims claims = getAllClaimsFromToken(token);
        List<SimpleGrantedAuthority> authorities = null;
        if (claims.get("roles") != null) {
          authorities = ((List<String>) claims.get("roles")).stream().map(role-> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
        }
        return new User(
          claims.getSubject(),
          "",
          authorities);
      } catch (Exception e) {
        return null;
      }
    }
    public Date getExpirationDateFromToken(String token) {
      return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
      final Claims claims = getAllClaimsFromToken(token);
      return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
      return Jwts.parser()
        .setSigningKey(secret)
        .parseClaimsJws(token)
        .getBody();
    }

    public Boolean isTokenExpired(String token) {
      final Date expiration = getExpirationDateFromToken(token);
      return expiration.before(new Date());
    }

    public String generateToken(User account) {
      return doGenerateToken(account.getUsername());
    }

    public String doGenerateToken(String subject) {

      Claims claims = Jwts.claims().setSubject(subject);
      claims.put("scopes", Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));

      return Jwts.builder()
        .setClaims(claims)
        .setExpiration(new Date(System.currentTimeMillis() + expiration))
        .signWith(SignatureAlgorithm.HS256, secret)
        .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
      final String username = getUsernameFromToken(token);
      return (
        username.equals(userDetails.getUsername())
          && !isTokenExpired(token));
    }
}
