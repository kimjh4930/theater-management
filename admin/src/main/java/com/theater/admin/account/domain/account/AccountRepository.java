package com.theater.admin.account.domain.account;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {
    Long save(Account account);
    List<Account> findAll();
    Optional<Account> findById(Long id);
    Optional<Account> findByEmail(String email);
    Long updateRole (Long id, AccountRole role);
//    boolean isExist (String email);
    boolean existByEmail (String email);
    boolean existByNickname (String nickname);
}
