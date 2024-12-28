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

        // Lấy danh sách lịch sử đăng nhập theo accountId theo thứ tự giảm dần của ngày đăng nhập
        List<LoginHistory> loginHistories = loginHistoryRepository.findByAccountIdOrderByLoginDateDesc(accountId);

        int newStreakCount = 1; // Mặc định streakCount là 1

        // Kiểm tra nếu có lịch sử đăng nhập trước đó
        if (!loginHistories.isEmpty()) {
            LoginHistory lastLogin = loginHistories.get(0);
            LocalDate lastLoginDate = lastLogin.getLoginDate();

            // Nếu ngày đăng nhập gần nhất là hôm qua, tăng streakCount lên
            if (lastLoginDate.equals(today.minusDays(1))) {
                newStreakCount = lastLogin.getStreakCount() + 1; // Đăng nhập liên tiếp
            }
            // Nếu ngày đăng nhập gần nhất là cùng ngày hôm nay (nếu có nhiều lần đăng nhập trong ngày)
            else if (lastLoginDate.equals(today)) {
                newStreakCount = lastLogin.getStreakCount(); // Không thay đổi streakCount
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
