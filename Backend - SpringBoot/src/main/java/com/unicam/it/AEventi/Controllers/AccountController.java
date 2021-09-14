package com.unicam.it.AEventi.Controllers;

import com.mysql.cj.xdevapi.JsonParser;
import com.unicam.it.AEventi.Models.Account;
import com.unicam.it.AEventi.Services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
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

        // SELEZIONARE TUTTI  GLI ACCOUNT
        // Aggregate root
        // tag::get-aggregate-root[]
        @GetMapping("/accounts")
        public List<Account> all() {
            return service.getAccount();
        }
        // end::get-aggregate-root[]

        // AGGIUNGERE UN ACCOUNT
        @PostMapping("/accounts")
        public Account newAccount(@RequestBody Account newAccount) {
            return service.createAccount(newAccount);

        }

    // LOGGARE CON UN ACCOUNT
    @PostMapping("/accounts/auth")
    public Account login(@RequestParam String email, @RequestParam String password) {
        return service.getAccountbyMail((email), password);

    }
    @GetMapping("/accounts/auth")
    public Account get(@RequestParam String email, @RequestParam String password) {
        return service.getAccountbyMail(email, password);

    }



        //AGGIORNARE UN ACCOUNT
        @RequestMapping( value = "/accounts/{id}", method = RequestMethod.PUT)
        public Account replaceAccount(@RequestBody Account newAccount, @PathVariable Long id) {

            return service.updateAccount(newAccount,id);
        }

        //RIMUOVERE UN ACCOUNT
        @DeleteMapping("/accounts/{id}")
        public void deleteAccount(@PathVariable Long id) {
            service.deleteAccount(id);
        }
    }


