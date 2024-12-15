package com.example.mobile.DTO;

public class TopicLearnedStatsDTO {
    private Long topicId;
    private String topicName; // Tên chủ đề (giả sử bạn có trường topicName trong bảng Topic)
    private long learnedCount; // Số từ đã học
    private long totalCount; // Tổng số từ trong chủ đề

    // Getters and Setters
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

    public long getLearnedCount() {
        return learnedCount;
    }

    public void setLearnedCount(long learnedCount) {
        this.learnedCount = learnedCount;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }
}
