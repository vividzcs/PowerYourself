package com.nwuer.core.entity;

public class Policy {
    private String id;

    private Boolean oneTimeTask;

    private Integer every;

    private Integer frequency;

    public Policy(String id, Boolean oneTimeTask, Integer every, Integer frequency) {
        this.id = id;
        this.oneTimeTask = oneTimeTask;
        this.every = every;
        this.frequency = frequency;
    }

    public Policy() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Boolean getOneTimeTask() {
        return oneTimeTask;
    }

    public void setOneTimeTask(Boolean oneTimeTask) {
        this.oneTimeTask = oneTimeTask;
    }

    public Integer getEvery() {
        return every;
    }

    public void setEvery(Integer every) {
        this.every = every;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }
}