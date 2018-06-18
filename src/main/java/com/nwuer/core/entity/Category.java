package com.nwuer.core.entity;

public class Category {
    private String id;

    private String categoryName;

    private String userId;

    private String parentId;

    public Category(String id, String categoryName, String userId, String parentId) {
        this.id = id;
        this.categoryName = categoryName;
        this.userId = userId;
        this.parentId = parentId;
    }

    public Category() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }
}