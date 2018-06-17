package com.nwuer.core.entity;

import java.time.LocalDateTime;

public class Task {
    private String id;

    private String title;

    private LocalDateTime beganTime;

    private LocalDateTime endTime;

    private Boolean allDayTask;

    private Boolean taskModified;

    private String remindTime;

    private String repeatPolicyId;

    private String repetitionEndConditionId;

    private String location;

    private String notation;

    private String taskCategoryId;

    public Task(String id, String title, LocalDateTime beganTime, LocalDateTime endTime, Boolean allDayTask, Boolean taskModified, String remindTime, String repeatPolicyId, String repetitionEndConditionId, String location, String notation, String taskCategoryId) {
        this.id = id;
        this.title = title;
        this.beganTime = beganTime;
        this.endTime = endTime;
        this.allDayTask = allDayTask;
        this.taskModified = taskModified;
        this.remindTime = remindTime;
        this.repeatPolicyId = repeatPolicyId;
        this.repetitionEndConditionId = repetitionEndConditionId;
        this.location = location;
        this.notation = notation;
        this.taskCategoryId = taskCategoryId;
    }

    public Task() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public LocalDateTime getBeganTime() {
        return beganTime;
    }

    public void setBeganTime(LocalDateTime beganTime) {
        this.beganTime = beganTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Boolean getAllDayTask() {
        return allDayTask;
    }

    public void setAllDayTask(Boolean allDayTask) {
        this.allDayTask = allDayTask;
    }

    public Boolean getTaskModified() {
        return taskModified;
    }

    public void setTaskModified(Boolean taskModified) {
        this.taskModified = taskModified;
    }

    public String getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(String remindTime) {
        this.remindTime = remindTime == null ? null : remindTime.trim();
    }

    public String getRepeatPolicyId() {
        return repeatPolicyId;
    }

    public void setRepeatPolicyId(String repeatPolicyId) {
        this.repeatPolicyId = repeatPolicyId == null ? null : repeatPolicyId.trim();
    }

    public String getRepetitionEndConditionId() {
        return repetitionEndConditionId;
    }

    public void setRepetitionEndConditionId(String repetitionEndConditionId) {
        this.repetitionEndConditionId = repetitionEndConditionId == null ? null : repetitionEndConditionId.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getNotation() {
        return notation;
    }

    public void setNotation(String notation) {
        this.notation = notation == null ? null : notation.trim();
    }

    public String getTaskCategoryId() {
        return taskCategoryId;
    }

    public void setTaskCategoryId(String taskCategoryId) {
        this.taskCategoryId = taskCategoryId == null ? null : taskCategoryId.trim();
    }
}