package com.theater.admin.account.adapter.infrastructure;

import com.theater.admin.account.domain.account.Account;
import com.theater.admin.account.domain.account.AccountRepository;
import com.theater.admin.account.domain.account.AccountRole;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class H2AccountRepository implements AccountRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public H2AccountRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Long save(Account account) {
        entityManager.persist(account);
        entityManager.flush();

        return account.getId();
    }

    @Override
    public List<Account> findAll() {
        return entityManager.createQuery("select account from Account account", Account.class)
                .getResultList();
    }

    @Override
    public Optional<Account> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Account.class, id));
    }

    @Override
    public Optional<Account> findByEmail(String email) {
        Account findAccount = entityManager.createQuery(
                "select account from Account account where account.email.email =: email",Account.class)
                .setParameter("email", email)
                .getSingleResult();

        return Optional.ofNullable(findAccount);
    }

    @Override
    public Long updateRole(Long id, AccountRole role) {
        Account account = findById(id)
                .orElseThrow(() -> new NullPointerException("해당 직원이 없습니다."));

        account.changeRoleTo(role);

        return account.getId();
    }


    @Override
    public boolean existByEmail(String email) {
        Account findAccount = entityManager.createQuery(
          "select account from Account account where account.email.email =: email", Account.class)
                .setParameter("email", email)
                .getSingleResult();
        return findAccount != null;
    }

    @Override
    public boolean existByNickname(String nickname) {
        Account findAccount = entityManager.createQuery(
                "select account from Account account where account.nickname.name =: nickname", Account.class)
                .setParameter("nickname", nickname)
                .getSingleResult();

        return findAccount != null;
    }
}
