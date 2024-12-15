package com.example.mobile.repository;

import com.example.mobile.entity.WordType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordTypeRepository extends JpaRepository<WordType, Long> {
}
