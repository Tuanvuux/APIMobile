package com.example.mobile.repository;

import com.example.mobile.entity.Synonym;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysnonymRepository extends JpaRepository<Synonym, Long> {
}
