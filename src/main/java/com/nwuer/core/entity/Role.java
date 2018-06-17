package com.nwuer.core.entity;

public class Role {
    private String id;

    private Integer level;

    public Role(String id, Integer level) {
        this.id = id;
        this.level = level;
    }

    public Role() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}