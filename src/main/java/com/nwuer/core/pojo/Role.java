package com.nwuer.core.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author harbo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    /**
     * 角色ID
     */
    private String id;

    /**
     * 角色等级，1是管理员，10是普通用户
     */
    private int level;

    public static Role roleAdmin = new Role( "0517a45e-de80-43a0-93af-8b7a25f4ecdf", 1);
    public static Role roleOrdinaryUser = new Role( "0f71aeee-6ca3-481f-8386-93d930482b92", 10);
}
