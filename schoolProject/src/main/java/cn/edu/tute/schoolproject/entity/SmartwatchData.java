package cn.edu.tute.schoolproject.entity;

import java.time.LocalDateTime;

public class SmartwatchData {
    private Integer id;
    private Integer userId;
    private Integer heartRate;
    private Integer stepCount;
    private Float sleepDuration;
    private Integer bloodOxygen;
    private LocalDateTime recordedAt;
    private LocalDateTime createdAt;

    // 构造函数
    public SmartwatchData() {
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getHeartRate() {
        return heartRate;
    }

    public Integer getStepCount() {
        return stepCount;
    }

    public Float getSleepDuration() {
        return sleepDuration;
    }

    public Integer getBloodOxygen() {
        return bloodOxygen;
    }

    public LocalDateTime getRecordedAt() {
        return recordedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setHeartRate(Integer heartRate) {
        this.heartRate = heartRate;
    }

    public void setStepCount(Integer stepCount) {
        this.stepCount = stepCount;
    }

    public void setSleepDuration(Float sleepDuration) {
        this.sleepDuration = sleepDuration;
    }

    public void setBloodOxygen(Integer bloodOxygen) {
        this.bloodOxygen = bloodOxygen;
    }

    public void setRecordedAt(LocalDateTime recordedAt) {
        this.recordedAt = recordedAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    // toString 方法
    @Override
    public String toString() {
        return "SmartwatchData{" +
                "id=" + id +
                ", userId=" + userId +
                ", heartRate=" + heartRate +
                ", stepCount=" + stepCount +
                ", sleepDuration=" + sleepDuration +
                ", bloodOxygen=" + bloodOxygen +
                ", recordedAt=" + recordedAt +
                ", createdAt=" + createdAt +
                '}';
    }
}