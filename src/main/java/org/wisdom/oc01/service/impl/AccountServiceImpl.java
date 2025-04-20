package org.wisdom.oc01.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.wisdom.oc01.entity.Account;
import org.wisdom.oc01.entity.Role;
import org.wisdom.oc01.entity.User;
import org.wisdom.oc01.exception.ErrorHandler;
import org.wisdom.oc01.generic.GeneralService;
import org.wisdom.oc01.generic.IRepository;
import org.wisdom.oc01.repository.AccountRepository;
import org.wisdom.oc01.repository.RoleRepository;
import org.wisdom.oc01.service.AccountService;

import java.util.Iterator;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private GeneralService generalService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void save(Account account) {
        try {
            // Kiểm tra username đã tồn tại
            if (accountRepository.findByUsername(account.getUsername()).isPresent()) {
                throw new ErrorHandler(HttpStatus.BAD_REQUEST, "Username already exists.");
            }

            // Validate mật khẩu
            generalService.validatePassword(account.getPassword());

            // Gán vai trò mặc định
            Role role = roleRepository.findById(1)
                    .orElseThrow(() -> new ErrorHandler(HttpStatus.BAD_REQUEST, "Role with ID 1 not found"));

            account.setRole(role);
            account.setPassword(passwordEncoder.encode(account.getPassword()));

            // Tạo User và liên kết với Account
            User user = new User();
            user.setAccount(account); // Gắn tài khoản vào User
            account.setUser(user); // Gắn User vào tài khoản

            // Lưu tài khoản (cascade sẽ tự lưu User)
            accountRepository.save(account);
        } catch (Exception e) {
            throw new ErrorHandler(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public void saveStudent(Account account) {
        try {
            // Kiểm tra username đã tồn tại
            if (accountRepository.findByUsername(account.getUsername()).isPresent()) {
                throw new ErrorHandler(HttpStatus.BAD_REQUEST, "Username already exists.");
            }

            // Validate mật khẩu
            generalService.validatePassword(account.getPassword());

            // Gán vai trò mặc định
            Role role = roleRepository.findById(3)
                    .orElseThrow(() -> new ErrorHandler(HttpStatus.BAD_REQUEST, "Role with ID 3 not found"));

            account.setRole(role);
            account.setPassword(passwordEncoder.encode(account.getPassword()));

            // Tạo User và liên kết với Account
            User user = new User();
            user.setAccount(account); // Gắn tài khoản vào User
            account.setUser(user); // Gắn User vào tài khoản

            // Lưu tài khoản (cascade sẽ tự lưu User)
            accountRepository.save(account);
        } catch (Exception e) {
            throw new ErrorHandler(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public void delete(Integer integer) {

    }


    @Override
    public Iterator<Account> findAll() {
        return null;
    }

    @Override
    public Account findOne(Integer integer) {
        return null;
    }


    @Override
    public IRepository<Account, Integer> getRepository() {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> account = accountRepository.findByUsername(username);
        return account.orElseThrow(() -> new ErrorHandler(HttpStatus.UNAUTHORIZED, "Account not exist"));
    }
}
