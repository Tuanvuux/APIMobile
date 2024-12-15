package com.example.mobile.repository;

import com.example.mobile.entity.LearnHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LearnHistoryRepository extends JpaRepository<LearnHistory, Long> {
    long countByAccountId(Long accountId);
    LearnHistory findByAccountIdAndVocabId(Long accountId, Long vocabId);

    @Query("SELECT DISTINCT v.topicId FROM LearnHistory lh JOIN Vocab v ON lh.vocabId = v.vocabId WHERE lh.accountId = :accountId")
    List<Long> findDistinctTopicIdsByAccountId(Long accountId);


    @Query("""
        SELECT COUNT(l) 
        FROM LearnHistory l 
        WHERE l.accountId = :accountId 
          AND l.vocabId IN (SELECT v.vocabId FROM Vocab v WHERE v.topicId = :topicId)
    """)
    Long countLearnedWordsByAccountAndTopic(@Param("accountId") Long accountId, @Param("topicId") Long topicId);
}
