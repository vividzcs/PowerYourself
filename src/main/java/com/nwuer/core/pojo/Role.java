package com.nwuer.core.pojo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author harbo
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Role {
    ADMIN("1"),
    ORDINARY("10");
    /**
     * 角色等级，1是管理员，10是普通用户
     */
    private String level;
}
