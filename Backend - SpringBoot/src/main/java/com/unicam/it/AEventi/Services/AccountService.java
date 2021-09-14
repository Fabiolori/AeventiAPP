package com.unicam.it.AEventi.Services;

import com.unicam.it.AEventi.Models.Account;
import com.unicam.it.AEventi.Repo.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {


        @Autowired
        AccountRepository repository;

        public Account createAccount(Account account) {

            return repository.save(account);
        }

        public Account updateAccount(Account newAccount, long id) {
            return repository.findById(id)
                    .map(account -> {
                        account.setUsername(newAccount.getUsername());
                        account.setPassword(newAccount.getPassword());
                        account.setName(newAccount.getName());
                        account.setSurname(newAccount.getSurname());
                        return repository.save(account);
                    })
                    .orElseGet(() -> {
                        newAccount.setId(id);
                        return repository.save(newAccount);
                    });

        }



        public void deleteAccount(long id) {
            repository.deleteById(id);

        }

        public List<Account> getAccount() {
            return repository.findAll();
        }

        public Account getAccountbyMail(String email, String password) {
            return repository.findAll().stream().filter(a -> a.getUsername().equals(email) && a.getPassword().equals(password)).findFirst()
                    .orElseThrow(NullPointerException::new);
        }






}
