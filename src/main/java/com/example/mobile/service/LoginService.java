package com.example.mobile.service;

import com.example.mobile.entity.LoginHistory;
import com.example.mobile.repository.LoginHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoginService {

    @Autowired
    private LoginHistoryRepository loginHistoryRepository;

    // Hàm cập nhật lịch sử đăng nhập và trả về streakCount
    public int updateLoginHistory(Long accountId) {
        LocalDate today = LocalDate.now();
        List<LoginHistory> loginHistories = loginHistoryRepository.findByAccountIdOrderByLoginDateDesc(accountId);

        int newStreakCount = 1; // Mặc định streakCount là 1

        if (!loginHistories.isEmpty()) {
            LoginHistory lastLogin = loginHistories.get(0);
            LocalDate lastLoginDate = lastLogin.getLoginDate();

            if (lastLoginDate.equals(today.minusDays(1))) {
                newStreakCount = lastLogin.getStreakCount() + 1; // Tăng streakCount nếu đăng nhập liên tiếp
            }
        }

        // Lưu thông tin đăng nhập mới
        LoginHistory newLogin = new LoginHistory();
        newLogin.setAccountId(accountId);
        newLogin.setLoginDate(today);
        newLogin.setStreakCount(newStreakCount);
        loginHistoryRepository.save(newLogin);

        // Trả về streakCount sau khi lưu
        return newStreakCount;
    }

    public List<LoginHistory> getLoginHistoryByAccountId(Long accountId) {
        return loginHistoryRepository.findByAccountIdOrderByLoginDateDesc(accountId);
    }
}
