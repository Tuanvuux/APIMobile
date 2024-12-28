package com.example.mobile.controller;
import com.example.mobile.DTO.TopicLearnedStatsDTO;
import com.example.mobile.entity.LearnHistory;
import com.example.mobile.entity.Topic;
import com.example.mobile.entity.Vocab;
import com.example.mobile.repository.LearnHistoryRepository;
import com.example.mobile.repository.TopicRepository;
import com.example.mobile.repository.VocabRepository;
import com.example.mobile.service.LearnStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/learned")
public class LearnHistoryController {

    @Autowired
    LearnHistoryRepository learnHistoryRepository;
    @Autowired
    TopicRepository topicRepository;
    @Autowired
    VocabRepository vocabRepository;

    @PostMapping("/vocabLearn")
    public boolean saveLearnHistory(@RequestBody LearnHistory learnHistory) {
        LearnHistory historyExits = learnHistoryRepository.findByAccountIdAndVocabId(learnHistory.getAccountId(), learnHistory.getVocabId());
        if (historyExits == null) {
            learnHistoryRepository.save(learnHistory);
            return true;  // Lưu thành công
        }
        return false;  // Lịch sử học đã tồn tại
    }




    @GetMapping("/total/{accountId}")
    public long getTotalLearnedVocab(@PathVariable long accountId) {
        return learnHistoryRepository.countByAccountId(accountId);
    }
    @GetMapping("/topic")
    public List<TopicLearnedStatsDTO> getListLearnedVocab(@RequestParam long accountId) {
        List<TopicLearnedStatsDTO> topicLearnedStatsDTOList = new ArrayList<>();
        List<Topic> topicList = topicRepository.findAll();
        for (Topic topic : topicList) {
            TopicLearnedStatsDTO topicLearnedStatsDTO = new TopicLearnedStatsDTO();
            topicLearnedStatsDTO.setTopicId(topic.getTopicId());
            topicLearnedStatsDTO.setTopicName(topic.getTopicName());
            topicLearnedStatsDTO.setLearnedCount(learnHistoryRepository.countLearnedWordsByAccountAndTopic(accountId, topic.getTopicId()));
            topicLearnedStatsDTO.setTotalCount(vocabRepository.countByTopicId(topic.getTopicId()));
            topicLearnedStatsDTOList.add(topicLearnedStatsDTO);
        }
        return topicLearnedStatsDTOList;
    }
    @GetMapping("/vocabLearnedByTopic")
    public List<Vocab> getLearnedVocabByTopicAndAccount(@RequestParam long accountId, @RequestParam long topicId) {
        return vocabRepository.findLearnedVocabByAccountIdAndTopicId(accountId, topicId);
    }

}