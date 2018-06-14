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
    private int id;
    private String name;

    public static Role roleAdmin = new Role( 0, "ADMIN");
    public static Role roleOrdinaryUser = new Role( 1, "ORDINARY_USER");
}
