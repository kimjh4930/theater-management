package com.theater.admin.account.adapter.presentation.web;

import com.theater.admin.account.domain.account.Account;
import com.theater.admin.account.domain.account.AccountRole;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class StoredAccount {
    private Long id;
    private String email;
    private String name;
    private String nickname;
    private AccountRole accountRole;
    private LocalDateTime joinedAt;

    protected StoredAccount () {}

    public StoredAccount (Account account){
        this.id = account.getId();
        this.email = account.getEmail();
        this.name = account.getName();
        this.nickname = account.getNickname();
        this.accountRole = account.getRole();
        this.joinedAt = account.getJoinedAt();
    }
}
