package com.nwuer.core.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author harbo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    /*
      用户uuid
     */
    private String id;

    private String username;
    private String nickname;
    private String password;

    private String email;

    private Role role;

}
