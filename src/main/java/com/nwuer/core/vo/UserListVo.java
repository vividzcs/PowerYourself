package com.nwuer.core.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author vividzc
 * @date 2018/6/26 0:07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserListVo {
    private String id;
    private String username;
    private String email;
    private Date lastLoginDatetime;
    private Boolean activated;
}
