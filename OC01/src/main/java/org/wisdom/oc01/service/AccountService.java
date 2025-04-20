package org.wisdom.oc01.service;


import org.springframework.security.core.userdetails.UserDetailsService;
import org.wisdom.oc01.entity.Account;
import org.wisdom.oc01.generic.IRepository;
import org.wisdom.oc01.generic.IService;

public interface AccountService extends IService<Account, Integer>, UserDetailsService {
    IRepository<Account, Integer> getRepository();

    void saveStudent(Account account);

}
