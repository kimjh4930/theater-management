package com.theater.admin.account.application;

import com.theater.admin.account.adapter.presentation.web.NewAccount;
import com.theater.admin.account.domain.account.Account;
import com.theater.admin.account.domain.account.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public Long saveNewAccount (NewAccount newAccount){
        Account account = new Account.Builder(
                newAccount.getEmail(),
                newAccount.getName(),
                newAccount.getNickname(),
                newAccount.getPassword()
            )
            .build();

        return accountRepository.save(account);
    }
}
