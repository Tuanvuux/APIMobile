package com.example.mobile.entity;

import jakarta.persistence.*;

@Entity(name = "Topic")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long topicId;
    String topicName;
    String topicStrans;
    String topicImg;

    public String getTopicStrans() {
        return topicStrans;
    }

    public void setTopicStrans(String topicStrans) {
        this.topicStrans = topicStrans;
    }



    public String getTopicImg() {
        return topicImg;
    }

    public void setTopicImg(String topicImg) {
        this.topicImg = topicImg;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }
}
