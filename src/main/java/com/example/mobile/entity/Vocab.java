package com.example.mobile.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Vocab {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vocabId;
    private String vocab;
    private String meaning;
    private String transcription;
    private String example;
    private Long wordTypeId;
    private String vocabImg;

    public String getVocabImg() {
        return vocabImg;
    }

    public void setVocabImg(String vocabImg) {
        this.vocabImg = vocabImg;
    }

    public int getVocabId() {
        return vocabId;
    }

    public void setVocabId(int vocabId) {
        this.vocabId = vocabId;
    }

    public String getVocab() {
        return vocab;
    }

    public void setVocab(String vocab) {
        this.vocab = vocab;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getTranscription() {
        return transcription;
    }

    public void setTranscription(String transcription) {
        this.transcription = transcription;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public Long getWordTypeId() {
        return wordTypeId;
    }

    public void setWordTypeId(Long wordTypeId) {
        this.wordTypeId = wordTypeId;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    private Long topicId;
}
