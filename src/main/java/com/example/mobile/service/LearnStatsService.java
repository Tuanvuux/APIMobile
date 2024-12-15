package com.example.mobile.service;

import com.example.mobile.repository.LearnHistoryRepository;
import com.example.mobile.repository.VocabRepository;
import org.springframework.stereotype.Service;

@Service
public class LearnStatsService {

    private final LearnHistoryRepository learnHistoryRepository;
    private final VocabRepository vocabRepository;

    public LearnStatsService(LearnHistoryRepository learnHistoryRepository, VocabRepository vocabRepository) {
        this.learnHistoryRepository = learnHistoryRepository;
        this.vocabRepository = vocabRepository;
    }

    public LearningProgress getLearningProgressByTopic(Long accountId, Long topicId) {
        Long learnedWords = learnHistoryRepository.countLearnedWordsByAccountAndTopic(accountId, topicId);
        Long totalWords = vocabRepository.countByTopicId(topicId);
        return new LearningProgress(learnedWords, totalWords);
    }

    public static class LearningProgress {
        private Long learnedWords;
        private Long totalWords;

        public LearningProgress(Long learnedWords, Long totalWords) {
            this.learnedWords = learnedWords;
            this.totalWords = totalWords;
        }

        public Long getLearnedWords() {
            return learnedWords;
        }

        public void setLearnedWords(Long learnedWords) {
            this.learnedWords = learnedWords;
        }

        public Long getTotalWords() {
            return totalWords;
        }

        public void setTotalWords(Long totalWords) {
            this.totalWords = totalWords;
        }
    }
}
