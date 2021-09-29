package com.unicam.it.AEventi.Controllers;

import com.mysql.cj.xdevapi.JsonParser;
import com.unicam.it.AEventi.Models.Account;
import com.unicam.it.AEventi.Models.MessageResponse;
import com.unicam.it.AEventi.Services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:8100")
@RestController
public class AccountController {
        @Autowired
        AccountService service;

        public AccountController(AccountService service) {
            this.service = service;
        }


        // AGGIUNGERE UN ACCOUNT
        @PostMapping("/public/accounts")
        public ResponseEntity<?> newAccount(@RequestBody Account newAccount) {
          if(newAccount.getUsername()== null ){
            return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Cannot create without username!"));
          }
        if (newAccount.getUsername() == service.loadUserByUsername(newAccount.getUsername()).getUsername() && service.loadUserByUsername(newAccount.getUsername()) != null ) {
          return ResponseEntity
            .badRequest()
            .body(new MessageResponse("Account already exist!"));
        }

            return ResponseEntity.ok(service.createAccount(newAccount));

        }

        //AGGIORNARE UN ACCOUNT
        @RequestMapping( value = "/accounts/{id}", method = RequestMethod.PUT)
        public Account replaceAccount(@RequestBody Account newAccount, @PathVariable String username) {

            return service.updateAccount(newAccount,username);
        }

        //RIMUOVERE UN ACCOUNT
        @DeleteMapping("/accounts/{id}")
        public void deleteAccount(@PathVariable String username) {
            service.deleteAccount(username);
        }
    }


