package com.example.mobile.controller;
import com.example.mobile.entity.Account;
import com.example.mobile.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    // API lấy thông tin người dùng theo accountId
    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccountById(@PathVariable long accountId) {
        return accountService.getAccountById(accountId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // API sửa thông tin người dùng theo accountId
    @PostMapping("/{accountId}")
    public ResponseEntity<Account> updateAccount(@PathVariable long accountId, @RequestBody Account updatedAccount) {
        try {
            Account account = accountService.updateAccount(accountId, updatedAccount);
            return ResponseEntity.ok(account);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/register")
    public ResponseEntity<Account> registerAccount(@RequestBody Account newAccount) {
        try {
            Account account = accountService.registerAccount(newAccount); // gọi service để đăng ký
            return ResponseEntity.status(201).body(account); // Trả về status 201 nếu thành công
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).build(); // Trả về lỗi nếu có lỗi
        }
    }

}
