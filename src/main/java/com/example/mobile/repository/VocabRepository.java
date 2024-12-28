package com.example.mobile.repository;

import com.example.mobile.entity.Vocab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VocabRepository extends JpaRepository<Vocab, Long> {
    List<Vocab> findByTopicId(Long topicId);

    @Query(value = "SELECT TOP 15 * FROM vocab ORDER BY NEWID()", nativeQuery = true)
    List<Vocab> findRandomVocab();

    @Query(value = "SELECT TOP 15 * FROM vocab WHERE topic_id = :topicId ORDER BY NEWID()", nativeQuery = true)
    List<Vocab> findRandomVocabByTopicId(@Param("topicId") Long topicId);

    @Query("SELECT COUNT(v) FROM Vocab v WHERE v.topicId = :topicId")
    long countByTopicId(Long topicId);

    long count();
    @Query("""
        SELECT v 
        FROM Vocab v 
        WHERE v.topicId = :topicId 
          AND v.vocabId IN (SELECT lh.vocabId FROM LearnHistory lh WHERE lh.accountId = :accountId)
    """)
    List<Vocab> findLearnedVocabByAccountIdAndTopicId(@Param("accountId") Long accountId, @Param("topicId") Long topicId);


}
