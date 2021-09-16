package com.unicam.it.AEventi.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.unicam.it.AEventi.Models.User;
import com.unicam.it.AEventi.Security.JwtAuthenticationRequest;
import com.unicam.it.AEventi.Security.JwtTokenUtil;
import com.unicam.it.AEventi.Services.AccountService;
import com.unicam.it.AEventi.Services.AutenticationResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin(origins = "http://localhost:8100")
@RestController
public class LoginController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/public/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletResponse response) throws AuthenticationException, JsonProcessingException {

      // Effettuo l autenticazione
      final Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
          authenticationRequest.getUsername(),
          authenticationRequest.getPassword()
        )
      );
      SecurityContextHolder.getContext().setAuthentication(authentication);

      // Genero Token
      final User user = accountService.loadUserByUsername(authenticationRequest.getUsername());
      final String token = jwtTokenUtil.generateToken(user);
      response.setHeader(tokenHeader,token);
      // Ritorno il token
      return ResponseEntity.ok(new AutenticationResponseService(user.getUsername(),user.getAuthorities()));
    }

    @RequestMapping(value = "protected/refresh-token", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request, HttpServletResponse response) {
      String token = request.getHeader(tokenHeader);
      UserDetails userDetails =
        (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

      if (jwtTokenUtil.isTokenExpired(token)) {
        String refreshedToken = jwtTokenUtil.doGenerateToken(token);
        response.setHeader(tokenHeader,refreshedToken);

        return ResponseEntity.ok(new AutenticationResponseService(userDetails.getUsername(),userDetails.getAuthorities()));
      } else {
        return ResponseEntity.badRequest().body(null);
      }
    }

  }


