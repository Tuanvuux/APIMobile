package com.example.mobile.controller;
import com.example.mobile.entity.Vocab;
import com.example.mobile.repository.VocabRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/vocab")
public class VocabController {
    @Autowired
    VocabRepository vocabRepository;

    @GetMapping("/topic/{topicId}")
    public List<Vocab> getVocab(@PathVariable long topicId) {
        return vocabRepository.findByTopicId(topicId);
    }

    @GetMapping("/test")
    public List<Vocab> getVocabRanDom() {
        var vocal =  vocabRepository.findRandomVocab();
        return vocal;
    }
    @GetMapping("/testTopic/{topicId}")
    public List<Vocab> getVocabTopicRanDom(@PathVariable long topicId) {
        return vocabRepository.findRandomVocabByTopicId(topicId);
    }
    @GetMapping("/count")
    public long getVocabCount() {
        return vocabRepository.count();
    }



}
