package com.theater.admin.account.adapter.infrastructure;

import com.theater.admin.account.domain.account.Account;
import com.theater.admin.account.domain.account.AccountRepository;
import com.theater.admin.account.domain.account.AccountRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class H2AccountRepositoryTest {

    @Autowired
    private AccountRepository repository;

    @Autowired
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    @DisplayName("데이터베이스에 등록한 모든 계정을 조회한다.")
    @Test
    void findAll (){
        //given

        //when
        List<Account> accounts = repository.findAll();

        //then
        accounts.forEach(System.out::println);
        assertThat(accounts.size()).isNotZero();
    }

    @Transactional
    @DisplayName("Account를 저장 후 조회한다.")
    @Test
    void save (){
        //given
        Account newAccount = new Account.Builder(
                "kimjh4930@nate.com",
                "Kim Junha",
                "jhkim1",
                "password")
                .build();

        //when
        Long id = repository.save(newAccount);

        //then
        System.out.println("New Account : " + newAccount);
        assertThat(id).isNotNull();
        assertThat(newAccount.getRole()).isEqualTo(AccountRole.UNAUTHORIZED);
    }

    @Transactional
    @DisplayName("Account의 Role 을 MANAGER로 변경한다.")
    @Test
    void updateRole (){
        //given
        Account account = repository.findByEmail("kimjh4930@nate.com")
                .orElseThrow(() -> new NullPointerException());

        //when
        repository.updateRole(account.getId(), AccountRole.MANAGER);
        entityManager.flush();

        //then
        Account findAccount = repository.findById(account.getId())
                .orElseThrow(() -> new NullPointerException());

        assertThat(findAccount.getRole()).isEqualTo(AccountRole.MANAGER);
    }

}