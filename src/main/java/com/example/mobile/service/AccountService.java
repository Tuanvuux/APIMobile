package com.example.mobile.service;

import com.example.mobile.DTO.AccountDTO;
import com.example.mobile.entity.Account;
import com.example.mobile.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    // Lấy thông tin người dùng theo accountId
    public Optional<Account> getAccountById(long accountId) {
        return accountRepository.findById(accountId);
    }

    // Sửa thông tin người dùng theo accountId
    public Account updateAccount(long accountId, Account updatedAccount) {
        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            account.setAddress(updatedAccount.getAddress());
            account.setFullName(updatedAccount.getFullName());
            account.setBirthday(updatedAccount.getBirthday());
            return accountRepository.save(account);
        } else {
            throw new RuntimeException("Account not found with id: " + accountId);
        }
    }
    public Account registerAccount(Account newAccount) {
        // Kiểm tra nếu tài khoản đã tồn tại
        if (accountRepository.existsByUserName(newAccount.getUserName())) {
            throw new RuntimeException("Username already exists");
        }

        // Mã hóa mật khẩu (nếu cần thiết) và lưu tài khoản mới
        // Ví dụ: newAccount.setPassword(passwordEncoder.encode(newAccount.getPassword()));
        return accountRepository.save(newAccount);
    }
}