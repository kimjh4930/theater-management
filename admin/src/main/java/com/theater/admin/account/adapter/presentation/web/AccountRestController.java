package com.theater.admin.account.adapter.presentation.web;

import com.theater.admin.account.domain.account.AccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/accounts")
public class AccountRestController {
    private final AccountRepository accountRepository;

    public AccountRestController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping
    public ResponseEntity<List<StoredAccount>> findAll (){
        List<StoredAccount> accounts = accountRepository.findAll()
                .stream()
                .map(StoredAccount::new)
                .collect(Collectors.toUnmodifiableList());

        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoredAccount> findOne (@PathVariable("id") Long id){
        StoredAccount account = accountRepository.findById(id)
                .map(StoredAccount::new)
                .orElseThrow(() -> new NullPointerException());

        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<StoredAccount> findOneByEmail (@PathVariable("email") String email){
        System.out.println("email : " + email);
        StoredAccount account = accountRepository.findByEmail(email)
                .map(StoredAccount::new)
                .orElseThrow(() -> new NullPointerException());

        return new ResponseEntity<>(account, HttpStatus.OK);
    }
}
