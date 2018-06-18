package com.nwuer.core.entity;

import java.time.LocalDateTime;

public class Condition {
    private String id;

    private Integer policy;

    private Boolean never;

    private LocalDateTime time;

    private Integer frequency;

    public Condition(String id, Integer policy, Boolean never, LocalDateTime time, Integer frequency) {
        this.id = id;
        this.policy = policy;
        this.never = never;
        this.time = time;
        this.frequency = frequency;
    }

    public Condition() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getPolicy() {
        return policy;
    }

    public void setPolicy(Integer policy) {
        this.policy = policy;
    }

    public Boolean getNever() {
        return never;
    }

    public void setNever(Boolean never) {
        this.never = never;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }
}