package com.example.mobile.service;

import com.example.mobile.DTO.AccountDTO;
import com.example.mobile.entity.Account;
import com.example.mobile.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    public AccountDTO getAccountByUsername(String userName) {
        Account account = accountRepository.findByUserName(userName);
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setFullName(account.getFullName());
        accountDTO.setAddress(account.getAddress());
        accountDTO.setBirthday(account.getBirthday());
        if (accountDTO != null) {
            return accountDTO;
        } else {
            throw new RuntimeException("Account not found with username " + userName);
        }
    }
}
