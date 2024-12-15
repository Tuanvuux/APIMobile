package com.example.mobile.controller;

import com.example.mobile.entity.Account;
import com.example.mobile.entity.Topic;
import com.example.mobile.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/topic")
public class TopicController {
    @Autowired
    TopicRepository topicRepository;
    @GetMapping
    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }
}
