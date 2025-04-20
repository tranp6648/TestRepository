package org.wisdom.oc01.repository;


import org.springframework.stereotype.Repository;
import org.wisdom.oc01.entity.Account;
import org.wisdom.oc01.entity.User;
import org.wisdom.oc01.generic.IRepository;

import java.util.Optional;

@Repository
public interface AccountRepository extends IRepository<Account, Integer> {
    Optional<Account> findByUsername(String username);

    Optional<Account> findByEmail(String email);

    String user(User user);
}
