package com.example.mobile.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class WordType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int wordTypeId;
    private String wordTypeName;

    public int getWordTypeId() {
        return wordTypeId;
    }

    public void setWordTypeId(int wordTypeId) {
        this.wordTypeId = wordTypeId;
    }

    public String getWordTypeName() {
        return wordTypeName;
    }

    public void setWordTypeName(String wordTypeName) {
        this.wordTypeName = wordTypeName;
    }
}
