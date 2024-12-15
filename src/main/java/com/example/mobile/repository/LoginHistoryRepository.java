package com.example.mobile.repository;

import com.example.mobile.entity.LoginHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginHistoryRepository extends JpaRepository<LoginHistory, Long> {
    List<LoginHistory> findByAccountIdOrderByLoginDateDesc(Long accountId);
}
