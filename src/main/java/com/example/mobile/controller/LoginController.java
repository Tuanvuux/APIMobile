package com.example.mobile.controller;
import com.example.mobile.DTO.AccountDTO;
import com.example.mobile.entity.Account;
import com.example.mobile.repository.AccountRepository;
import com.example.mobile.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private LoginService loginService;

    @PostMapping
    public ResponseEntity<AccountDTO> login(@RequestParam String username, @RequestParam String password) {
        Account account = accountRepository.findByUserName(username);
        if (account == null) {
            // Trả về lỗi 404 khi không tìm thấy tài khoản
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        if (!account.getPassword().equals(password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        int streakCount = loginService.updateLoginHistory(account.getAccountId());

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setFullName(account.getFullName());
        accountDTO.setAddress(account.getAddress());
        accountDTO.setBirthday(account.getBirthday());
        accountDTO.setImage(account.getImage());
        accountDTO.setStreakCount(streakCount);
        return ResponseEntity.ok(accountDTO);
    }
}
